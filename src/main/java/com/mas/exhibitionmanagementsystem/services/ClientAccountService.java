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

@Service
public class ClientAccountService {
    private final ClientRepository clientRepository;
    private final UserAccountRepository userAccountRepository;

    public ClientAccountService(ClientRepository clientRepository, UserAccountRepository userAccountRepository) {
        this.clientRepository = clientRepository;
        this.userAccountRepository = userAccountRepository;
    }

    public boolean authenticate(String email, String password) {
        UserAccount userAccount = userAccountRepository.findByEmail(email);
        if (userAccount == null) return false;

        return decrypt(password, userAccount.getPassword());
    }

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

    private boolean validateEmail(String email) {
        String emailRegex = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    public Client addClient(String name, String surname, LocalDate birthdate) {
        Client newClient = new Client();
        newClient.setName(name);
        newClient.setSurname(surname);
        newClient.setBirthDate(birthdate);
        clientRepository.save(newClient);

        return newClient;
    }

    public boolean register(String email, String password, Client client) {
        if (userAccountRepository.findByEmail(email) != null)
            return false;
        UserAccount newUserAccount = new UserAccount();
        newUserAccount.setEmail(email);
        newUserAccount.setPassword(encrypt(password));
        userAccountRepository.save(newUserAccount);

        client.setAccount(newUserAccount);
        clientRepository.save(client);
        return true;
    }

    public Client findByEmail(String email) {
        return clientRepository.findByAccount_Email(email);
    }

    private String encrypt(String inputPassword) {
        return passwordEncoder().encode(inputPassword);
    }

    private boolean decrypt(String inputPassword, String hashedPassword) {
        return passwordEncoder().matches(inputPassword, hashedPassword);
    }

    private static BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
