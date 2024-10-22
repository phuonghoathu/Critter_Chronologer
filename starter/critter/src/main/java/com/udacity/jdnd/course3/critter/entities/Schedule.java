package com.udacity.jdnd.course3.critter.entities;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;


@Data
@Entity
@Table(name="tb_schedules")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    // Table tb_schedules_employee_ids will create auto
    @ManyToMany(fetch = FetchType.LAZY, targetEntity = Employee.class)
    private List<Employee> employee;

    // Table tb_schedules_pet_ids will create auto
    @ManyToMany(fetch = FetchType.LAZY, targetEntity = Pet.class)
    private List<Pet> pet;

    @ElementCollection
    private Set<EmployeeSkill> skills;
}
