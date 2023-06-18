package com.mas.exhibitionmanagementsystem.controllers;

import com.mas.exhibitionmanagementsystem.services.ReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/reservation")
    public String makeReservation(Model model) {
        return "reservation";
    }
}
