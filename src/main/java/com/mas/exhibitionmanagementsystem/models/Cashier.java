package com.mas.exhibitionmanagementsystem.models;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

public class Cashier extends Employee {

    @Column(name = "is_student")
    private boolean isStudent;

    public boolean isStudent() {
        return isStudent;
    }

    public void setStudent(boolean student) {
        isStudent = student;
    }
}
