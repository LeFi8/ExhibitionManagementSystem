package com.mas.exhibitionmanagementsystem.services;

import com.mas.exhibitionmanagementsystem.models.Exhibition;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ExhibitionReservationService {
    private final ExhibitionService exhibiitonService;
    private final ReservationService reservationService;

    public ExhibitionReservationService(ExhibitionService exhibiitonService, ReservationService reservationService) {
        this.exhibiitonService = exhibiitonService;
        this.reservationService = reservationService;
    }
    public boolean isAvailable(Exhibition exhibition, int numberOfReservations, LocalDate date) {
        int reservationsMadeThatDay = reservationService.countReservationsOnExhibitionByDate(exhibition, date);
        int spaceAvailableOnExhibition = exhibiitonService.spaceOnExhibition(exhibition, date);
        return spaceAvailableOnExhibition - reservationsMadeThatDay - numberOfReservations >= 0;
    }
}
