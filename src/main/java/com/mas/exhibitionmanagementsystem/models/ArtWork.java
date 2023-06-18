package com.mas.exhibitionmanagementsystem.models;

import jakarta.persistence.*;

@Entity
@Table(name = "art_work")
public class ArtWork {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "creation_year", nullable = false)
    private int creationYear;
}
