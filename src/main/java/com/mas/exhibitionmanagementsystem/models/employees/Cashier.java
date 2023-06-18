package com.mas.exhibitionmanagementsystem.models.employees;

import jakarta.persistence.Column;

public class Cashier extends Employee {
    @Column(name = "is_student", nullable = false)
    private boolean isStudent;

    public boolean isStudent() {
        return isStudent;
    }

    public void setStudent(boolean student) {
        isStudent = student;
    }
}
