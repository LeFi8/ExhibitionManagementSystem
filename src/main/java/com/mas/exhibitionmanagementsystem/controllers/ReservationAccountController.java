package com.mas.exhibitionmanagementsystem.controllers;

import com.mas.exhibitionmanagementsystem.models.Client;
import com.mas.exhibitionmanagementsystem.models.Exhibition;
import com.mas.exhibitionmanagementsystem.models.Reservation;
import com.mas.exhibitionmanagementsystem.models.enums.AudioGuide;
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
import java.util.Enumeration;

/**
 * Controller class for managing reservations and accounts
 */
@Controller
public class ReservationAccountController {
    private final ReservationService reservationService;
    private final ClientAccountService clientAccountService;

    public ReservationAccountController(ReservationService reservationService, ClientAccountService clientAccountService) {
        this.reservationService = reservationService;
        this.clientAccountService = clientAccountService;
    }

    /**
     * Method used for displaying login screen
     *
     * @param request contains session data, used for getting the name of selected exhibition
     * @param model used for passing data to the view
     * @return path to the view
     */
    @GetMapping("/login")
    public String login(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        String exhibitionName = ((Exhibition)session.getAttribute("exhibition")).getName();
        model.addAttribute("exhibitionName", exhibitionName);
        return "reservation-login";
    }

    /**
     * Method used for login and user authentication
     *
     * @param email user email
     * @param password user password
     * @param request stores session data
     * @param model used for passing data to the view
     * @return path to the view or redirection to next reservation step
     */
    @PostMapping("/login")
    public String authenticate(@RequestParam("email") String email,
                               @RequestParam("password") String password,
                               HttpServletRequest request,
                               Model model) {
        HttpSession session = request.getSession();

        if (!clientAccountService.authenticate(email, password)) {
            String exhibitionName = ((Exhibition)session.getAttribute("exhibition")).getName();
            model.addAttribute("exhibitionName", exhibitionName);
            model.addAttribute("email", email);
            model.addAttribute("badPass", "Email or password incorrect");
            return "reservation-login";
        }

        Client client = clientAccountService.findByEmail(email);
        session.setAttribute("client", client);
        session.setAttribute("clientEmail", email);

        return "redirect:reservation/final-step";
    }

    /**
     * Method for displaying sign up screen
     * @return path to the view
     */
    @GetMapping("/registration")
    public String registerForm() {
        return "reservation-sign-up";
    }

    /**
     * Add new client to database, create and assign account to him
     *
     * @param email user email
     * @param password user password
     * @param password2 second password, used for validation
     * @param name user's name
     * @param surname user's surname
     * @param birthdate user's birthdate, can be null
     * @param request contains session data
     * @param model used for passing data to the view
     * @return path to the view or redirection to next reservation step
     */
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
        session.setAttribute("client", client);
        session.setAttribute("clientEmail", email);

        return "redirect:reservation/final-step";
    }

    /**
     * Show reservation final step screen, where user can check if his data is correct, if the user
     * is not logged in, he can input his data
     *
     * @param request contains session data
     * @param model used for passing data to the view
     * @return path to the view
     */
    @GetMapping("/reservation/final-step")
    public String reservationFinal(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        String exhibitionName = ((Exhibition)session.getAttribute("exhibition")).getName();
        Client client = (Client) session.getAttribute("client");

        model.addAttribute("exhibitionName", exhibitionName);
        model.addAttribute("reservationCount", session.getAttribute("reservationCount"));
        model.addAttribute("reservationDate", session.getAttribute("reservationDate"));

        if (client != null) {
            model.addAttribute("name", client.getName());
            model.addAttribute("surname", client.getSurname());
            model.addAttribute("email", session.getAttribute("clientEmail"));
        }
        return "reservation-final";
    }

    /**
     * Confirms making reservation, if user is not logged in, the client is added to database
     * without account, reservation data is also added to database here,
     * all data from except for reservation info is removed from the session
     *
     * @param name user's name
     * @param surname user's surname
     * @param email user's email
     * @param request contains session data
     * @param model used for passing data to the view
     * @return redirection to reservation confirmation screen
     */
    @PostMapping("reservation/final-step")
    public String confirmReservation(@RequestParam("name") String name,
                                     @RequestParam("surname") String surname,
                                     @RequestParam("email") String email,
                                     HttpServletRequest request,
                                     Model model) {

        if (clientAccountService.isValidationFailed(name, surname, email, model))
            return "reservation-final";

        HttpSession session = request.getSession();
        int reservationCount = (int) session.getAttribute("reservationCount");
        LocalDate reservationDate = ((LocalDate) session.getAttribute("reservationDate"));
        AudioGuide audioGuide = AudioGuide.valueOf((String) session.getAttribute("reservationAudioGuide"));
        Client client = (Client) session.getAttribute("client");
        Exhibition exhibition = (Exhibition) session.getAttribute("exhibition");

        if (client == null) client = clientAccountService.addClient(name, surname, null);

        Reservation reservation = reservationService.makeReservation(
                reservationDate, reservationCount, audioGuide, client, exhibition);

        Enumeration<String> attributeNames = session.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String attributeName = attributeNames.nextElement();
            session.removeAttribute(attributeName);
        }
        session.setAttribute("reservation", reservation);

        return "redirect:/reservation/confirmation";
    }
}
