package com.mas.exhibitionmanagementsystem.controllers;

import com.mas.exhibitionmanagementsystem.models.Exhibition;
import com.mas.exhibitionmanagementsystem.services.ExhibitionService;
import com.mas.exhibitionmanagementsystem.services.ReservationService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class ExhibitionReservationController {
    private final ExhibitionService exhibitionService;
    private final ReservationService reservationService;

    public ExhibitionReservationController(ExhibitionService exhibitionService, ReservationService reservationService) {
        this.exhibitionService = exhibitionService;
        this.reservationService = reservationService;
    }

    @GetMapping("/exhibition/{id}/reservation")
    public String makeReservation(@PathVariable Long id, Model model) {
        Exhibition exhibition = exhibitionService.getExhibitionById(id).orElse(new Exhibition());
        model.addAttribute("exhibition", exhibition);
        return "reservation";
    }

    @PostMapping("/exhibition/{id}/reservation/check-availability")
    public String checkAvailability(@PathVariable("id") Long id,
                                    @RequestParam("reservationNumber") int reservationNumber,
                                    @RequestParam("reservationDate") LocalDate reservationDate,
                                    @RequestParam("reservationAudioGuide") String audioGuide,
                                    Model model) {
        Exhibition exhibition = exhibitionService.getExhibitionById(id).orElse(new Exhibition());
        //TODO: logic
        return "redirect:/availability";
    }
}
