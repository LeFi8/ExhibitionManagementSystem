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

    public Long getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public List<Exhibition> getExhibition() {
        return exhibition;
    }

    public void setExhibition(List<Exhibition> exhibition) {
        this.exhibition = exhibition;
    }
}
