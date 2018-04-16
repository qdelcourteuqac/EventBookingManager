package main.java.eventbookingmanager.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import main.java.eventbookingmanager.models.AuthenticationRequest;
import org.springframework.security.core.Authentication;

public interface AuthenticationService {

	Authentication getAuthentication(Jws<Claims> request);
	Authentication authenticate(AuthenticationRequest authenticationRequest);

}
