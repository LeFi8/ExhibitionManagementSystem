package com.mas.exhibitionmanagementsystem.services;

import com.mas.exhibitionmanagementsystem.models.UserAccount;
import com.mas.exhibitionmanagementsystem.repositories.UserAccountRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserAccountService {
    private final UserAccountRepository userAccountRepository;

    public UserAccountService(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    public boolean authenticate(String email, String password) {
        UserAccount userAccount = userAccountRepository.findByEmail(email);
        if (userAccount == null) return false;

        return decrypt(password, userAccount.getPassword());
    }

    public boolean validateEmail(String email) {
        String emailRegex = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    public void register(String email, String password) {
        UserAccount newUserAccount = new UserAccount();
        newUserAccount.setEmail(email);
        newUserAccount.setPassword(password);
        userAccountRepository.save(newUserAccount);
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
