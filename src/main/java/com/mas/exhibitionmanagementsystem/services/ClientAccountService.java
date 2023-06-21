package com.mas.exhibitionmanagementsystem.services;

import com.mas.exhibitionmanagementsystem.models.Client;
import com.mas.exhibitionmanagementsystem.models.UserAccount;
import com.mas.exhibitionmanagementsystem.repositories.ClientRepository;
import com.mas.exhibitionmanagementsystem.repositories.UserAccountRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Service class for managing clients and their accounts
 */
@Service
public class ClientAccountService {
    private final ClientRepository clientRepository;
    private final UserAccountRepository userAccountRepository;

    public ClientAccountService(ClientRepository clientRepository, UserAccountRepository userAccountRepository) {
        this.clientRepository = clientRepository;
        this.userAccountRepository = userAccountRepository;
    }

    /**
     * Authenticates user
     *
     * @param email user's email
     * @param password user's password
     * @return true is successful, false otherwise
     */
    public boolean authenticate(String email, String password) {
        UserAccount userAccount = userAccountRepository.findByEmail(email);
        if (userAccount == null) return false;

        return checkMatchingPasswords(password, userAccount.getPassword());
    }

    /**
     * Adds new client to the database
     *
     * @param name client's name
     * @param surname client's surname
     * @param birthdate client's birthdate
     * @return added client
     */
    public Client addClient(String name, String surname, LocalDate birthdate) {
        Client newClient = new Client();
        newClient.setName(name);
        newClient.setSurname(surname);
        newClient.setBirthDate(birthdate);
        try {
            clientRepository.save(newClient);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return newClient;
    }

    /**
     * Registers new user
     *
     * @param email user's email
     * @param password user's password
     * @param client whose account is to be registered
     * @return true if success, false otherwise
     */
    public boolean register(String email, String password, Client client) {
        if (userAccountRepository.findByEmail(email) != null)
            return false;
        UserAccount newUserAccount = new UserAccount();
        newUserAccount.setEmail(email);
        newUserAccount.setPassword(encrypt(password));

        try {
            userAccountRepository.save(newUserAccount);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

        client.setAccount(newUserAccount);

        try {
            clientRepository.save(client);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    /**
     * Validates the signup form for a new client account.
     *
     * @param email user's email
     * @param password user's password
     * @param password2 second password for validation
     * @param name user's name
     * @param surname user's surname
     * @param birthdate user's birthdate
     * @param model used for passing data to the view
     * @return true if success, false otherwise
     */
    public boolean validateSignupForm(
            String email,
            String password,
            String password2,
            String name,
            String surname,
            LocalDate birthdate,
            Model model
    ) {
        if (isValidationFailed(name, surname, email, model)) return false;

        if (password.length() < 6) {
            model.addAttribute("email", email);
            model.addAttribute(
                    "badPassword",
                    "Password is too short, it needs to have at least 6 characters"
            );
            return false;
        }

        if (!password2.equals(password)) {
            model.addAttribute("email", email);
            model.addAttribute(
                    "badSecondPassword",
                    "Passwords are not matching"
            );
            return false;
        }

        if (birthdate != null && birthdate.isAfter(LocalDate.now())) {
            model.addAttribute("email", email);
            model.addAttribute("name", name);
            model.addAttribute("surname", surname);
            model.addAttribute(
                    "dateError",
                    "Your birthdate cannot be after the current day!"
            );
            return false;
        }
        return true;
    }

    /**
     * Checks if validation fails
     *
     * @param name user's name
     * @param surname user's surname
     * @param email user's email
     * @param model used for passing data to the view
     * @return true if validation fails, false otherwise
     */
    public boolean isValidationFailed(String name, String surname, String email, Model model) {
        if (name.isEmpty()) {
            model.addAttribute("email", email);
            model.addAttribute(
                    "nameEmpty",
                    "Name can't be empty!"
            );
            return true;
        }

        if (surname.isEmpty()) {
            model.addAttribute("email", email);
            model.addAttribute(
                    "surnameEmpty",
                    "Surname can't be empty!"
            );
            return true;
        }

        if (!validateEmail(email)) {
            model.addAttribute("badMail", "Email badly formatted");
            return true;
        }
        return false;
    }

    /**
     * Validates email
     *
     * @param email mail to validate
     * @return true if valid, false otherwise
     */
    private boolean validateEmail(String email) {
        String emailRegex = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    /**
     * Find client by his email
     * @param email client's email
     * @return client if email found, null otherwise
     */
    public Client findByEmail(String email) {
        return clientRepository.findByAccount_Email(email);
    }

    /**
     * Encrypts the given raw password
     * @param inputPassword password to encrypt
     * @return encrypted password
     */
    private String encrypt(String inputPassword) {
        return passwordEncoder().encode(inputPassword);
    }

    /**
     * Checks if both passwords are matching
     * @param inputPassword inputed password
     * @param hashedPassword hashed password from database
     * @return true if matching, false otherwise
     */
    private boolean checkMatchingPasswords(String inputPassword, String hashedPassword) {
        return passwordEncoder().matches(inputPassword, hashedPassword);
    }

    private static BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
