package com.mas.exhibitionmanagementsystem.models.employees;

import com.mas.exhibitionmanagementsystem.models.Exhibition;
import jakarta.persistence.OneToOne;

public class ExhibitionManager extends Employee {
    @OneToOne(mappedBy = "exhibition")
    private Exhibition exhibition;
}
