package com.mas.exhibitionmanagementsystem.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "artist")
public class Artist extends Person{
    @ManyToMany
    @JoinTable(
            name = "artist_artwork",
            joinColumns = @JoinColumn(name = "id_artist"),
            inverseJoinColumns = @JoinColumn(name = "id_artwork")
    )
    private Set<ArtWork> artworks = new HashSet<>();
}
