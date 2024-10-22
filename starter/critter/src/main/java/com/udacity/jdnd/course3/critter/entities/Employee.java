package com.udacity.jdnd.course3.critter.entities;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import lombok.Data;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.Set;

@Data
@Entity
@Table(name="tb_employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Nationalized
    String name;

    // It will auto create table employee_days_available to save
    // If not want to create it, need create a convertor
    @ElementCollection
    private Set<DayOfWeek> daysAvailable;

    // It will auto create table employee_skills to save
    // If not want to create it, need create a convertor
    @ElementCollection
    private Set<EmployeeSkill> skills;
}
