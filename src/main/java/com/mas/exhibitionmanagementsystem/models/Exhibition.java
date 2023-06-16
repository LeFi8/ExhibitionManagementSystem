package com.mas.exhibitionmanagementsystem.models;

import com.mas.exhibitionmanagementsystem.utilities.converter.DateConverter;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Exhibition")
public class Exhibition {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "startDate", nullable = false)
    @Convert(converter = DateConverter.class)
    private LocalDate startDate;

    @Column(name = "endDate")
    @Convert(converter = DateConverter.class)
    private LocalDate endDate;

    @Column(name = "description", columnDefinition = "LONGTEXT", nullable = false)
    @Lob
    private String description;

    @Column(name = "ticketPrice", nullable = false)
    private double ticketPrice;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        if (endDate.isBefore(startDate))
            throw new IllegalArgumentException ("Exhibition end date cannot be before it has even started!");
        if (endDate.isEqual(startDate))
            throw new IllegalArgumentException ("End date cannot be on the same date as starting date!");
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

}
