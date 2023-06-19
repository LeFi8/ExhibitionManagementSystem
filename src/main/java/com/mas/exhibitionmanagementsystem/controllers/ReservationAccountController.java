package com.mas.exhibitionmanagementsystem.controllers;

import com.mas.exhibitionmanagementsystem.services.ReservationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReservationAccountController {
    private final ReservationService reservationService;

    public ReservationAccountController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/login")
    public String login(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        model.addAttribute("exhibitionName", session.getAttribute("exhibitionName"));
        return "reservation-login";
    }

    @PostMapping("/register")
    public String registration(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        model.addAttribute("exhibitionName", session.getAttribute("exhibitionName"));
        return "reservation-sign-up";
    }
}
