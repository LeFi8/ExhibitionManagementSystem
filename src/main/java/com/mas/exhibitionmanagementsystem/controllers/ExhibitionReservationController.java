package com.mas.exhibitionmanagementsystem.controllers;

import com.mas.exhibitionmanagementsystem.models.Exhibition;
import com.mas.exhibitionmanagementsystem.services.ExhibitionReservationService;
import com.mas.exhibitionmanagementsystem.services.ExhibitionService;
import com.mas.exhibitionmanagementsystem.services.ReservationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
public class ExhibitionReservationController {
    private final ExhibitionService exhibitionService;
    private final ExhibitionReservationService exhibitionReservationService;

    public ExhibitionReservationController(ExhibitionService exhibitionService, ExhibitionReservationService exhibitionReservationService) {
        this.exhibitionService = exhibitionService;
        this.exhibitionReservationService = exhibitionReservationService;
    }

    @GetMapping("/exhibition/{id}/reservation")
    public String makeReservation(@PathVariable Long id, Model model) {
        Exhibition exhibition = exhibitionService.getExhibitionById(id).orElse(new Exhibition());
        model.addAttribute("exhibition", exhibition);
        model.addAttribute("reservationCount", 1);
        model.addAttribute("reservationDate", LocalDate.now());
        model.addAttribute("nextButtonDisabled", true);
        return "reservation";
    }

    @PostMapping("/exhibition/{id}/reservation/check-availability")
    public String checkAvailability(HttpServletRequest request,
                                    @PathVariable("id") Long id,
                                    @RequestParam("reservation-count") int reservationNumber,
                                    @RequestParam("reservation-date") LocalDate reservationDate,
                                    Model model) {
        Exhibition exhibition = exhibitionService.getExhibitionById(id).orElse(new Exhibition());
        boolean isAvailableForReservation = exhibitionReservationService.isAvailable(exhibition, reservationNumber, reservationDate);

        if (!isAvailableForReservation) {
            model.addAttribute("exhibition", exhibition);
            model.addAttribute("reservationCount", reservationNumber);
            model.addAttribute("reservationDate", reservationDate);
            return "reservation-error";
        }
        HttpSession session = request.getSession();
        session.setAttribute("idExhibition", exhibition.getId());
        session.setAttribute("exhibitionName", exhibition.getName());
        session.setAttribute("reservationCount", reservationNumber);
        session.setAttribute("reservationDate", reservationDate);

        model.addAttribute("exhibition", exhibition);
        model.addAttribute("reservationCount", reservationNumber);
        model.addAttribute("reservationDate", reservationDate);
        model.addAttribute("available", "Reservation available");
        model.addAttribute("nextButtonDisabled", false);
        return "reservation";
    }

    @GetMapping("/reservation/account-options")
    public String showReservationAccountPage(HttpServletRequest request, Model model) {
        model.addAttribute("exhibitionName", request.getAttribute("exhibitionName"));
        return "reservation-account-options";
    }
}
