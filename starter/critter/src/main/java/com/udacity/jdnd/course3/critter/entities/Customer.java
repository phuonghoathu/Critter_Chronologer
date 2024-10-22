package com.udacity.jdnd.course3.critter.entities;

import lombok.Data;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="tb_customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Nationalized
    private String name;

    // CustomerDTO have this field but postman haven't => make it at entities
    private String notes;

    private String phoneNumber;

    @OneToMany(mappedBy = "customer", targetEntity = Pet.class, fetch = FetchType.EAGER)
    private List<Pet> pets;
}
