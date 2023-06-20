package com.mas.exhibitionmanagementsystem.models.employees;

import com.mas.exhibitionmanagementsystem.models.Exhibition;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

@Entity
public class ExhibitionManager extends Employee {
    @OneToOne(mappedBy = "exhibitionManager")
    private Exhibition exhibition;
}
