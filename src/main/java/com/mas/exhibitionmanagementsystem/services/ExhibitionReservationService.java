package com.mas.exhibitionmanagementsystem.services;

import com.mas.exhibitionmanagementsystem.models.Exhibition;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * Service class for managing exhibitions and reservations
 */
@Service
public class ExhibitionReservationService {
    private final ExhibitionService exhibiitonService;
    private final ReservationService reservationService;

    public ExhibitionReservationService(ExhibitionService exhibiitonService, ReservationService reservationService) {
        this.exhibiitonService = exhibiitonService;
        this.reservationService = reservationService;
    }

    /**
     * Checks if a reservation can be made to that specific exhibition on a specific date
     *
     * @param exhibition selected by client
     * @param numberOfReservations number of reservations a client wants to make
     * @param date of the reservation a client is interested in
     * @return true if is available, false otherwise
     */
    public boolean isAvailable(Exhibition exhibition, int numberOfReservations, LocalDate date) {
        int reservationsMadeThatDay = reservationService.countReservationsOnExhibitionByDate(exhibition, date);
        int spaceAvailableOnExhibition = exhibiitonService.spaceOnExhibition(exhibition, date);
        return spaceAvailableOnExhibition - reservationsMadeThatDay - numberOfReservations >= 0;
    }
}
