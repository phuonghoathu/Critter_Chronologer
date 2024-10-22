package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entities.Employee;
import com.udacity.jdnd.course3.critter.exception.EntityNotFoundException;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.user.EmployeeRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void updateAvailability(Set<DayOfWeek> daysAvailable, long employeeId) {
        Employee curEmp;
        try {
            curEmp =  employeeRepository.getOne(employeeId);
        } catch (Exception e) {
            throw new EntityNotFoundException("Employee not found with ID: " + employeeId);
        }
        curEmp.setDaysAvailable(daysAvailable);
        employeeRepository.save(curEmp);
    }

    public List<Employee> findEmployeesForService(EmployeeRequestDTO employeeDTO) {
        List<Employee> availableEmp = new ArrayList<>();
        List<Employee> availableInDate = this.employeeRepository.findEmployeesByDaysAvailable(employeeDTO.getDate().getDayOfWeek());

        for (Employee emp : availableInDate) {
            if (emp.getSkills().containsAll(employeeDTO.getSkills())) {
                availableEmp.add(emp);
            }
        }
        return availableEmp;
    }

    public Employee getEmployeeById(Long employeeId) {
        return employeeRepository.getOne(employeeId);
    }
}
