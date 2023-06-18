package com.mas.exhibitionmanagementsystem.models;

import com.mas.exhibitionmanagementsystem.utilities.converter.DateConverter;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pass")
public class Pass {
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

    @ManyToMany(mappedBy = "passSet")
    private Set<Exhibition> exhibitions = new HashSet<>();

    @OneToOne(mappedBy = "pass")
    private Client client;
}
