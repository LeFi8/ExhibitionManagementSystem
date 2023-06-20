package com.mas.exhibitionmanagementsystem.controllers;

import com.mas.exhibitionmanagementsystem.models.Client;
import com.mas.exhibitionmanagementsystem.services.ReservationService;
import com.mas.exhibitionmanagementsystem.services.ClientAccountService;
import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class ReservationAccountController {
    private final ReservationService reservationService;
    private final ClientAccountService clientAccountService;

    public ReservationAccountController(ReservationService reservationService, ClientAccountService clientAccountService) {
        this.reservationService = reservationService;
        this.clientAccountService = clientAccountService;
    }

    @GetMapping("/login")
    public String login(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        model.addAttribute("exhibitionName", session.getAttribute("exhibitionName"));
        return "reservation-login";
    }

    @PostMapping("/login/auth")
    public String authenticate(@RequestParam("email") String email,
                               @RequestParam("password") String password,
                               HttpServletRequest request,
                               Model model) {

        if (!clientAccountService.authenticate(email, password)) {
            HttpSession session = request.getSession();
            model.addAttribute("exhibitionName", session.getAttribute("exhibitionName"));
            model.addAttribute("email", email);
            model.addAttribute("badPass", "Email or password incorrect");
            return "reservation-login";
        }

        return "reservation-final";
    }

    @GetMapping("/register-form")
    public String registerForm() {
        return "reservation-sign-up";
    }

    @PostMapping("/register")
    public String registration(@RequestParam("email") String email,
                               @RequestParam("password") String password,
                               @RequestParam("secondPassword") String password2,
                               @RequestParam("name") String name,
                               @RequestParam("surname") String surname,
                               @RequestParam("birthdate") @Nullable LocalDate birthdate,
                               Model model) {

        if (!clientAccountService.validation(email, password, password2, name, surname, birthdate, model))
            return "reservation-sign-up";

        Client client = clientAccountService.addClient(name, surname, birthdate);
        clientAccountService.register(email, password, client);

        return "reservation-final";
    }

    @GetMapping("/reservation/final-step")
    public String reservationFinal(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        model.addAttribute("exhibitionName", session.getAttribute("exhibitionName"));
        model.addAttribute("reservationCount", session.getAttribute("reservationCount"));
        model.addAttribute("reservationDate", session.getAttribute("reservationDate"));
        return "reservation-final";
    }

    @PostMapping("/reservation/confirm")
    public String confirmReservation(){
        return "reservation-confirmation";
    }
}
