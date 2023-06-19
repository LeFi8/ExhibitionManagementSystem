package com.mas.exhibitionmanagementsystem.services;

import com.mas.exhibitionmanagementsystem.models.Exhibition;
import com.mas.exhibitionmanagementsystem.models.Reservation;
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
}
