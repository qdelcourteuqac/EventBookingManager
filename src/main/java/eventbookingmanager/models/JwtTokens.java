package main.java.eventbookingmanager.models;

public class JwtTokens {

    public String token;
    public String refreshToken;

    public JwtTokens(String token, String refreshToken) {
        this.token = token;
        this.refreshToken = refreshToken;
    }

}
