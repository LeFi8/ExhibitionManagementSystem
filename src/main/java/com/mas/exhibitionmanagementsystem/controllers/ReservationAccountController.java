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

    @PostMapping("/login")
    public String authenticate(@RequestParam("email") String email,
                               @RequestParam("password") String password,
                               HttpServletRequest request,
                               Model model) {
        HttpSession session = request.getSession();

        if (!clientAccountService.authenticate(email, password)) {
            model.addAttribute("exhibitionName", session.getAttribute("exhibitionName"));
            model.addAttribute("email", email);
            model.addAttribute("badPass", "Email or password incorrect");
            return "reservation-login";
        }

        Client client = clientAccountService.findByEmail(email);
        session.setAttribute("clientName", client.getName());
        session.setAttribute("clientSurname", client.getSurname());
        session.setAttribute("clientEmail", email);

        return "redirect:reservation/final-step";
    }

    @GetMapping("/registration")
    public String registerForm() {
        return "reservation-sign-up";
    }

    @PostMapping("/registration")
    public String registration(@RequestParam("email") String email,
                               @RequestParam("password") String password,
                               @RequestParam("secondPassword") String password2,
                               @RequestParam("name") String name,
                               @RequestParam("surname") String surname,
                               @RequestParam("birthdate") @Nullable LocalDate birthdate,
                               HttpServletRequest request,
                               Model model) {

        if (!clientAccountService.validateSignupForm(email, password, password2, name, surname, birthdate, model))
            return "reservation-sign-up";

        Client client = clientAccountService.addClient(name, surname, birthdate);
        if (!clientAccountService.register(email, password, client)) {
            model.addAttribute("badMail", "This mail is already in use with another account");
            return "reservation-sign-up";
        }

        HttpSession session = request.getSession();
        session.setAttribute("clientName", client.getName());
        session.setAttribute("clientSurname", client.getSurname());
        session.setAttribute("clientEmail", email);

        return "redirect:reservation/final-step";
    }

    @GetMapping("/reservation/final-step")
    public String reservationFinal(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        model.addAttribute("exhibitionName", session.getAttribute("exhibitionName"));
        model.addAttribute("reservationCount", session.getAttribute("reservationCount"));
        model.addAttribute("reservationDate", session.getAttribute("reservationDate"));
        model.addAttribute("name", session.getAttribute("clientName"));
        model.addAttribute("surname", session.getAttribute("clientSurname"));
        model.addAttribute("email", session.getAttribute("clientEmail"));
        return "reservation-final";
    }

    @PostMapping("reservation/final-step")
    public String confirmReservation(@RequestParam("name") String name,
                                     @RequestParam("surname") String surname,
                                     @RequestParam("email") String email,
                                     Model model) {

        if (clientAccountService.isValidationFailed(name, surname, email, model))
            return "reservation-final";

        return "redirect:/reservation/confirmation";
    }

    @GetMapping("/reservation/confirmation")
    public String showReservationConfirmation(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        model.addAttribute("exhibitionName", session.getAttribute("exhibitionName"));
        model.addAttribute("reservationCount", session.getAttribute("reservationCount"));
        model.addAttribute("reservationDate", session.getAttribute("reservationDate"));
        model.addAttribute("clientName", session.getAttribute("clientName"));

        return "reservation-confirmation";
    }
}
