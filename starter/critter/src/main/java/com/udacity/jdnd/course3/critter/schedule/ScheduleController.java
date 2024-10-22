package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.entities.Employee;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.entities.Schedule;
import com.udacity.jdnd.course3.critter.exception.EntityNotFoundException;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.service.PetService;
import com.udacity.jdnd.course3.critter.service.ScheduleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    PetService petService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        return convertScheduleToScheduleDTO(
                scheduleService.createSchedule(convertScheduleDTOToSchedule(scheduleDTO))
        ) ;
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        return convertScheduleListToScheduleDTOList(scheduleService.getAllSchedule());
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        return convertScheduleListToScheduleDTOList(scheduleService.getScheduleForPet(petId));
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        return convertScheduleListToScheduleDTOList(scheduleService.getScheduleForEmployee(employeeId));
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        return convertScheduleListToScheduleDTOList(scheduleService.getScheduleForCustomer(customerId));
    }

    public Schedule convertScheduleDTOToSchedule(ScheduleDTO scheduleDTO){
        Schedule schedule = new Schedule();

        BeanUtils.copyProperties(scheduleDTO, schedule);

        schedule.setSkills(scheduleDTO.getActivities());

        if(scheduleDTO.getEmployeeIds() != null && !scheduleDTO.getEmployeeIds().isEmpty()) {
            schedule.setEmployee(new ArrayList<>());
            for (Long empId: scheduleDTO.getEmployeeIds()) {
                schedule.getEmployee().add(employeeService.getEmployeeById(empId));
            }
        }

        if(scheduleDTO.getPetIds() != null && !scheduleDTO.getPetIds().isEmpty()) {
            schedule.setPet(new ArrayList<>());
            for (Long petId: scheduleDTO.getPetIds()) {
                schedule.getPet().add(petService.getPet(petId));
            }
        }

        return schedule;
    }

    public ScheduleDTO convertScheduleToScheduleDTO(Schedule schedule){
        ScheduleDTO scheduleDTO = new ScheduleDTO();

        try{
            BeanUtils.copyProperties(schedule, scheduleDTO);
        } catch (Exception e) {
            throw new EntityNotFoundException("Schedule not found");
        }
        scheduleDTO.setActivities(schedule.getSkills());

        if(schedule.getEmployee() != null && !schedule.getEmployee().isEmpty()) {
            scheduleDTO.setEmployeeIds(new ArrayList<>());
            for (Employee emp: schedule.getEmployee()) {
                scheduleDTO.getEmployeeIds().add(emp.getId());
            }
        }

        if(schedule.getPet() != null && !schedule.getPet().isEmpty()) {
            scheduleDTO.setPetIds(new ArrayList<>());
            for (Pet pet: schedule.getPet()) {
                scheduleDTO.getPetIds().add(pet.getId());
            }
        }

        return scheduleDTO;
    }

    public List<ScheduleDTO> convertScheduleListToScheduleDTOList(List<Schedule> lstSchedule){
        List<ScheduleDTO> lstScheduleDTO = new ArrayList<>();

        for (Schedule schedule: lstSchedule) {
            lstScheduleDTO.add(convertScheduleToScheduleDTO(schedule));
        }

        return lstScheduleDTO;
    }
}
