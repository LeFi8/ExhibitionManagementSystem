package com.mas.exhibitionmanagementsystem.repositories;

import com.mas.exhibitionmanagementsystem.models.Exhibition;
import com.mas.exhibitionmanagementsystem.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for managing reservations
 */
@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    /**
     * Retrieves all reservations from the database
     * @return list of all reservations
     */
    List<Reservation> findAll();

    /**
     * Retrieves reservation based on given id
     * @param id must not be {@literal null}.
     * @return Optional containing a reservation, empty Optional if none found
     */
    Optional<Reservation> findById(Long id);

    /**
     * Retrieves all reservations for a given exhibition and date.
     *
     * @param exhibition for which reservations are to be retrieved
     * @param date of the reservations to be retrieved
     * @return Optional containing a list of reservations for the specified exhibition and date,
     * or an empty Optional if none found
     */
    Optional<List<Reservation>> findAllByExhibitionAndReservationDate(Exhibition exhibition, LocalDate date);
}
