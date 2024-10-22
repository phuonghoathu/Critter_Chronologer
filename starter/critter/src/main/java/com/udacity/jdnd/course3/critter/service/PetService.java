package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PetService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PetRepository petRepository;

    public Pet savePet(Pet pet, Long customerId) {
        Customer cus = customerRepository.getOne(customerId);
        pet.setCustomer(cus);
        Pet newPet = petRepository.save(pet);
        cus.getPets().add(newPet);
        return newPet;
    }

    public Pet getPet(Long petId) {
        return petRepository.getOne(petId);
    }

    public List<Pet> getAllPet() {
        return petRepository.findAll();
    }

    public List<Pet> getAllPetByOwner(Long customerId) {
        return petRepository.findPetByCustomerId(customerId);
    }
}
