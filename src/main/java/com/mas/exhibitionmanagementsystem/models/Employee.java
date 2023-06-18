package com.mas.exhibitionmanagementsystem.models;


import com.mas.exhibitionmanagementsystem.models.enums.Education;
import com.mas.exhibitionmanagementsystem.utilities.converter.DateConverter;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Employee")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Employee extends Person{

    @Enumerated(EnumType.STRING)
    @Column(name = "education", nullable = false)
    private Education education;

    @Column(name = "salary", nullable = false)
    private double salary;

    @Column(name = "employment_from", nullable = false)
    @Convert(converter = DateConverter.class)
    private LocalDate employmentDate;

    public double getSalary() {
        return salary;
    }

    public void addBonus(double bonus){
        this.salary = salary + bonus;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public LocalDate getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(LocalDate employmentDate) {
        this.employmentDate = employmentDate;
    }
}

