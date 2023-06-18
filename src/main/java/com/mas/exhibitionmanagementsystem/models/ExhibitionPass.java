package com.mas.exhibitionmanagementsystem.models;

import com.mas.exhibitionmanagementsystem.utilities.converter.DateConverter;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "exhibiiton_pass")
public class ExhibitionPass {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long serialNumber;

    @Column(name = "creation_date", nullable = false)
    @Convert(converter = DateConverter.class)
    private LocalDate creationDate;

    @Column(name = "end_date")
    @Convert(converter = DateConverter.class)
    private LocalDate endDate;
}
