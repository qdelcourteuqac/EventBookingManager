package main.java.eventbookingmanager.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import main.java.eventbookingmanager.models.Account;
import main.java.eventbookingmanager.models.JwtTokens;
import org.springframework.security.core.Authentication;

public interface JwtTokenService {

    JwtTokens createTokens(Authentication authentication);
    String createToken(Account user);
    String createRefreshToken(Account user);

    JwtTokens refreshJwtToken(String token);
    Jws<Claims> validateJwtToken(String token);

}
