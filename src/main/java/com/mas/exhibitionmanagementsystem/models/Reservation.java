package com.mas.exhibitionmanagementsystem.models;

import com.mas.exhibitionmanagementsystem.models.enums.AudioGuide;
import com.mas.exhibitionmanagementsystem.utilities.converter.DateConverter;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "reservation")
public class Reservation {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reservation_date", nullable = false)
    @Convert(converter = DateConverter.class)
    private LocalDate reservationDate;

    @Column(name = "number_of_reservations", nullable = false)
    private int numberOfReservations;

    @Column(name = "audio_guide", nullable = false)
    private AudioGuide audioGuide;

    @ManyToOne
    @JoinColumn(name = "id_exhibition", nullable = false)
    private Exhibition exhibition;

    @ManyToOne
    @JoinColumn(name = "id_client", nullable = false)
    private Client client;

    public Long getId() {
        return id;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    public int getNumberOfReservations() {
        return numberOfReservations;
    }

    public void setNumberOfReservations(int numberOfReservations) {
        this.numberOfReservations = numberOfReservations;
    }

    public AudioGuide getAudioGuide() {
        return audioGuide;
    }

    public void setAudioGuide(AudioGuide audioGuide) {
        this.audioGuide = audioGuide;
    }

    public Exhibition getExhibition() {
        return exhibition;
    }

    public void setExhibition(Exhibition exhibition) {
        this.exhibition = exhibition;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
