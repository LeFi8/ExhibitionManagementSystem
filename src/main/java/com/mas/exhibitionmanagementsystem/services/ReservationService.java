package com.mas.exhibitionmanagementsystem.services;

import com.mas.exhibitionmanagementsystem.models.Client;
import com.mas.exhibitionmanagementsystem.models.Exhibition;
import com.mas.exhibitionmanagementsystem.models.Reservation;
import com.mas.exhibitionmanagementsystem.models.enums.AudioGuide;
import com.mas.exhibitionmanagementsystem.repositories.ReservationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Optional<Reservation> getReservationById(Long id) {
        return reservationRepository.findById(id);
    }

    public int countReservationsOnExhibitionByDate(Exhibition exhibition, LocalDate date) {
        List<Reservation> reservations = reservationRepository.findAllByExhibitionAndReservationDate(exhibition, date)
                .orElse(List.of());
        int reservationsCount = 0;
        for (Reservation reservation : reservations) {
            reservationsCount += reservation.getNumberOfReservations();
        }
        return reservationsCount;
    }

    public boolean makeReservation(LocalDate date,
                                   int reservationCount,
                                   AudioGuide audioGuide,
                                   Client client,
                                   Exhibition exhibition) {
        try {
            Reservation reservation = new Reservation();
            reservation.setReservationDate(date);
            reservation.setNumberOfReservations(reservationCount);
            reservation.setAudioGuide(audioGuide);
            reservation.setClient(client);
            reservation.setExhibition(exhibition);

            reservationRepository.save(reservation);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }
}
