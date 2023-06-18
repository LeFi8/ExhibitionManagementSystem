package com.mas.exhibitionmanagementsystem.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "location")
public class Location {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "max_capacity", nullable = false)
    private int maxCapacity;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private List<Exhibition> exhibition = new ArrayList<>();
}
