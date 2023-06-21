package com.mas.exhibitionmanagementsystem.controllers;

import com.mas.exhibitionmanagementsystem.models.Exhibition;
import com.mas.exhibitionmanagementsystem.models.Reservation;
import com.mas.exhibitionmanagementsystem.services.ExhibitionReservationService;
import com.mas.exhibitionmanagementsystem.services.ExhibitionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

/**
 * Controller class for managing exhibitions and reservations
 */
@Controller
public class ExhibitionReservationController {
    private final ExhibitionService exhibitionService;
    private final ExhibitionReservationService exhibitionReservationService;

    public ExhibitionReservationController(ExhibitionService exhibitionService, ExhibitionReservationService exhibitionReservationService) {
        this.exhibitionService = exhibitionService;
        this.exhibitionReservationService = exhibitionReservationService;
    }

    /**
     * Begin reservation process
     *
     * @param id of chosen exhibition
     * @param model used for passing data to the view
     * @return path to the view
     */
    @GetMapping("/exhibition/{id}/reservation")
    public String beginReservation(@PathVariable Long id, Model model) {
        Exhibition exhibition = exhibitionService.getExhibitionById(id).orElse(new Exhibition());
        model.addAttribute("exhibition", exhibition);
        model.addAttribute("reservationCount", 1);
        model.addAttribute("reservationDate", LocalDate.now());
        return "reservation";
    }

    /**
     * Checks availability of an exhibition for specific number of reservations
     * and on the specific day, then saves number of reservations and reservation date
     * to the session and disables the user input for that fields if available
     *
     * @param request contains session data
     * @param id of chosen exhibition
     * @param reservationNumber number of requested reservations
     * @param reservationDate date to make reservation for
     * @param model used for passing data to the view
     * @return path to the view reservation view or error view
     */
    @GetMapping("/exhibition/{id}/reservation/check-availability")
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
        session.setAttribute("exhibition", exhibition);
        session.setAttribute("reservationCount", reservationNumber);
        session.setAttribute("reservationDate", reservationDate);

        model.addAttribute("reservationCountDisabled", true);
        model.addAttribute("reservationDateDisabled", true);
        model.addAttribute("isAvailable", true);
        model.addAttribute("exhibition", exhibition);
        model.addAttribute("reservationCount", reservationNumber);
        model.addAttribute("reservationDate", reservationDate);
        model.addAttribute("available", "Reservation available");
        return "reservation";
    }

    /**
     * Continues with the reservation process, saves audio guide info to the session
     *
     * @param request contains session data
     * @param audioGuide users chosen audio guide
     * @return path to the view reservation view or error view
     */
    @GetMapping("/exhibition/reservation/next")
    public String continueReservation(HttpServletRequest request, @RequestParam("audio-guide") String audioGuide) {
        HttpSession session = request.getSession();
        session.setAttribute("reservationAudioGuide", audioGuide);

        return "redirect:/reservation/account-options";
    }

    /**
     * Shows account option page where you can either log in, sign up or
     * continue without any account
     *
     * @param request contains session data
     * @param model used for passing data to the view
     * @return path to the view
     */
    @GetMapping("/reservation/account-options")
    public String showReservationAccountPage(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        String exhibitionName = ((Exhibition)session.getAttribute("exhibition")).getName();
        model.addAttribute("exhibitionName", exhibitionName);
        return "reservation-account-options";
    }

    /**
     * Shows confirmation for the reservation a user has made
     *
     * @param request contains session data
     * @param model used for passing data to the view
     * @return path to the view
     */
    @GetMapping("/reservation/confirmation")
    public String showReservationConfirmation(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        Reservation reservation = (Reservation) session.getAttribute("reservation");
        model.addAttribute("reservation", reservation);

        return "reservation-confirmation";
    }
}
