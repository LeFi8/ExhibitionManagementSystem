package com.mas.exhibitionmanagementsystem.models.employees;

import com.mas.exhibitionmanagementsystem.models.Ticket;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

public class Cashier extends Employee {
    @Column(name = "is_student", nullable = false)
    private boolean isStudent;

    public boolean isStudent() {
        return isStudent;
    }

    public void setStudent(boolean student) {
        isStudent = student;
    }

    @OneToMany(mappedBy = "cashier")
    private List<Ticket> soldTickets = new ArrayList<>();
}
