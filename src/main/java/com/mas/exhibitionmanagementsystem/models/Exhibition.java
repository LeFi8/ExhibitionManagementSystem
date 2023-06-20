package com.mas.exhibitionmanagementsystem.models;

import com.mas.exhibitionmanagementsystem.models.employees.Employee;
import com.mas.exhibitionmanagementsystem.models.employees.ExhibitionManager;
import com.mas.exhibitionmanagementsystem.utilities.converter.DateConverter;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Exhibition")
public class Exhibition {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "start_date", nullable = false)
    @Convert(converter = DateConverter.class)
    private LocalDate startDate;

    @Column(name = "end_date")
    @Convert(converter = DateConverter.class)
    private LocalDate endDate;

    @Column(name = "description", columnDefinition = "LONGTEXT", nullable = false)
    @Lob
    private String description;

    @Column(name = "ticket_price", nullable = false)
    private double ticketPrice;

    @ManyToOne()
    @JoinColumn(name = "id_location")
    private Location location;

    @OneToMany(mappedBy = "exhibition")
    private List<Reservation> reservations = new ArrayList<>();

    @OneToMany(mappedBy = "exhibition")
    private List<ArtWork> artWorks = new ArrayList<>();

    @OneToMany(mappedBy = "exhibition")
    private List<Ticket> tickets = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "id_exhibition_manager")
    private ExhibitionManager exhibitionManager;

    @ManyToMany
    @JoinTable(
            name = "exhibition_pass",
            joinColumns = @JoinColumn(name = "id_exhibition"),
            inverseJoinColumns = @JoinColumn(name = "id_pass")
    )
    private Set<Pass> passSet = new HashSet<>();

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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public List<ArtWork> getArtWorks() {
        return artWorks;
    }

    public void setArtWorks(List<ArtWork> artWorks) {
        this.artWorks = artWorks;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Employee getExhibitionManager() {
        return exhibitionManager;
    }

    public void setExhibitionManager(ExhibitionManager exhibitionManager) {
        this.exhibitionManager = exhibitionManager;
    }

    public Set<Pass> getPassSet() {
        return passSet;
    }

    public void setPassSet(Set<Pass> passSet) {
        this.passSet = passSet;
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
