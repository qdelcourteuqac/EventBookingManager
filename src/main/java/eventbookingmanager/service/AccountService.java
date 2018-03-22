package main.java.eventbookingmanager.service;

import main.java.eventbookingmanager.models.Account;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

public class AccountService {

    /**
     * Return true if credentials are correct, false otherwise
     *
     * @param account     account to check
     * @param rawPassword raw password
     * @return boolean
     */
    public boolean checkCredentials(Account account, String rawPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(rawPassword, account.getPassword());
    }

    /**
     * Return encoded password
     *
     * @param rawPassword raw password
     * @return string
     */
    public String encodePassword(String rawPassword) {
        return new BCryptPasswordEncoder().encode(rawPassword);
    }
}
