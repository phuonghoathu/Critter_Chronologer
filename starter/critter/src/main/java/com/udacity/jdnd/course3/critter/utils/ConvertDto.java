package com.udacity.jdnd.course3.critter.utils;

import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.entities.Employee;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.entities.Schedule;
import com.udacity.jdnd.course3.critter.exception.EntityNotFoundException;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeDTO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class ConvertDto {
    public static Pet convertPetDTOToPet(PetDTO petDTO){
        Pet pet = new Pet();
        BeanUtils.copyProperties(petDTO, pet);
        return pet;
    }

    public static Customer convertCustomerDTOToCustomer(CustomerDTO customerDTO){
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        return customer;
    }

    public static PetDTO convertPetToPetDTO(Pet pet){
        PetDTO petDTO = new PetDTO();

        try{
            BeanUtils.copyProperties(pet, petDTO);
        } catch (Exception e) {
            throw new EntityNotFoundException("Pet not found");
        }
        if (pet.getCustomer() != null) {
            petDTO.setOwnerId(pet.getCustomer().getId());
        }
        return petDTO;
    }

    public static List<PetDTO> convertListPetToListPetDTO(List<Pet> lstPet){
        List<PetDTO> lstPetDTO = new ArrayList<>();

        for (Pet pet: lstPet) {
            lstPetDTO.add(convertPetToPetDTO(pet));
        }

        return lstPetDTO;
    }

    public static CustomerDTO convertCustomerToCustomerDTO(Customer customer){
        CustomerDTO customerDTO = new CustomerDTO();
        try{
            BeanUtils.copyProperties(customer, customerDTO);
        } catch (Exception e) {
            throw new EntityNotFoundException("Customer not found");
        }
        if (customer.getPets()!=null && !customer.getPets().isEmpty()) {
            for (Pet pet: customer.getPets()) {
                customerDTO.getPetIds().add(pet.getId());
            }
        }
        return customerDTO;
    }

    public static List<CustomerDTO> convertListCustomerToListCustomerDTO(List<Customer> lstCus){
        List<CustomerDTO> lstCustomerDTO = new ArrayList<>();

        for (Customer cus: lstCus) {
            lstCustomerDTO.add(convertCustomerToCustomerDTO(cus));
        }

        return lstCustomerDTO;
    }

    public static EmployeeDTO convertEmployeeToEmployeeDTO(Employee employee){
        EmployeeDTO employeeDTO = new EmployeeDTO();
        try{
            BeanUtils.copyProperties(employee, employeeDTO);
        } catch (Exception e) {
            throw new EntityNotFoundException("Employee not found");
        }

        return employeeDTO;
    }

    public static Employee convertEmployeeDTOToEmployee(EmployeeDTO employeeDTO){
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);

        return employee;
    }

    public static List<EmployeeDTO> convertListEmployeeToListEmployeeDTO(List<Employee> lstEmp){
        List<EmployeeDTO> lstEmployeeDTO = new ArrayList<>();

        for (Employee emp: lstEmp) {
            lstEmployeeDTO.add(convertEmployeeToEmployeeDTO(emp));
        }

        return lstEmployeeDTO;
    }


}
