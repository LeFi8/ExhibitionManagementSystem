package com.mas.exhibitionmanagementsystem.repositories;

import com.mas.exhibitionmanagementsystem.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findAll();
    Optional<Reservation> findById(Long id);
    Optional<List<Reservation>> findAllByReservationDate(LocalDate date);
}
