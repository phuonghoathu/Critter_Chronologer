package com.udacity.jdnd.course3.critter.entities;

import com.udacity.jdnd.course3.critter.pet.PetType;
import lombok.Data;
import org.hibernate.annotations.Nationalized;
import org.hibernate.type.LocalDateTimeType;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name="tb_pets")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private PetType type;

    @Nationalized
    private String name;

    // postman is "2019-12-16T04:43:57.995Z" => LocalDateTimeType
    // But PetDTO is LocalDate + birthDate need only YYYYMMDD => choose LocalDate
    private LocalDate birthDate;

    private String notes;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Customer.class, optional = false)
    @JoinColumn(name = "customer_id")
    private Customer customer;

}
