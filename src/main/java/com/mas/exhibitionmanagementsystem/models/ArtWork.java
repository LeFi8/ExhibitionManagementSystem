package com.mas.exhibitionmanagementsystem.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

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

    @ManyToOne()
    @JoinColumn(name = "id_exhibition")
    private Exhibition exhibition;

    @ManyToMany(mappedBy = "artworks")
    private Set<Artist> artists = new HashSet<>();
}
