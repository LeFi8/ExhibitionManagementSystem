package com.mas.exhibitionmanagementsystem.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "artwork")
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

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCreationYear() {
        return creationYear;
    }

    public void setCreationYear(int creationYear) {
        this.creationYear = creationYear;
    }

    public Exhibition getExhibition() {
        return exhibition;
    }

    public void setExhibition(Exhibition exhibition) {
        this.exhibition = exhibition;
    }

    public String getArtistsNames() {
        StringBuilder stringBuilder = new StringBuilder();
        artists.forEach(artist -> {
            stringBuilder.append(artist.getName());
            stringBuilder.append(" ");
            stringBuilder.append(artist.getSurname());
            stringBuilder.append('\n');
        });
        return stringBuilder.toString().trim();
    }

    public void setArtists(Set<Artist> artists) {
        this.artists = artists;
    }
}
