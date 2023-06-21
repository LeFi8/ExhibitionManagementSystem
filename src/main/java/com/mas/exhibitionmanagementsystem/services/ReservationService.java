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

/**
 * Service class for managing reservations
 */
@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    /**
     * Retrieves all reservations
     * @return list of all reservations
     */
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    /**
     * Retrieves reservation with given id
     * @param id of the reservation
     * @return Optional containing reservation or empty Optional if reservation not found
     */
    public Optional<Reservation> getReservationById(Long id) {
        return reservationRepository.findById(id);
    }

    /**
     * Counts the number of reservations on specific exhibition for a given day
     *
     * @param exhibition to count reservations on
     * @param date to count reservations for
     * @return total number of reservations on specific exhibition for a given day
     */
    public int countReservationsOnExhibitionByDate(Exhibition exhibition, LocalDate date) {
        List<Reservation> reservations = reservationRepository.findAllByExhibitionAndReservationDate(exhibition, date)
                .orElse(List.of());
        int reservationsCount = 0;
        for (Reservation reservation : reservations) {
            reservationsCount += reservation.getNumberOfReservations();
        }
        return reservationsCount;
    }

    /**
     * Creates a reservation
     *
     * @param date reservation date
     * @param reservationCount number of reservations
     * @param audioGuide specifies the audio guide option
     * @param client making the reservation
     * @param exhibition for which the reservation is made
     * @return created reservation
     */
    public Reservation makeReservation(LocalDate date,
                                       int reservationCount,
                                       AudioGuide audioGuide,
                                       Client client,
                                       Exhibition exhibition) {
        Reservation reservation = new Reservation();
        reservation.setReservationDate(date);
        reservation.setNumberOfReservations(reservationCount);
        reservation.setAudioGuide(audioGuide);
        reservation.setClient(client);
        reservation.setExhibition(exhibition);

        reservationRepository.save(reservation);

        return reservation;
    }
}
