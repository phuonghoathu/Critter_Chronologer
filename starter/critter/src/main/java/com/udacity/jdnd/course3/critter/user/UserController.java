package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.service.PetService;
import com.udacity.jdnd.course3.critter.utils.ConvertDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    CustomerService customerService;

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        return ConvertDto.convertCustomerToCustomerDTO(
                customerService.saveCustomer(ConvertDto.convertCustomerDTOToCustomer(customerDTO))) ;
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
        return ConvertDto.convertListCustomerToListCustomerDTO(customerService.getAll());
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
        return ConvertDto.convertCustomerToCustomerDTO(customerService.getOwnerByPetId(petId));
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return ConvertDto.convertEmployeeToEmployeeDTO(
                employeeService.saveEmployee(ConvertDto.convertEmployeeDTOToEmployee(employeeDTO))) ;
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        return ConvertDto.convertEmployeeToEmployeeDTO(employeeService.getEmployeeById(employeeId));
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        employeeService.updateAvailability(daysAvailable, employeeId);
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        return ConvertDto.convertListEmployeeToListEmployeeDTO(employeeService.findEmployeesForService(employeeDTO));
    }

}
