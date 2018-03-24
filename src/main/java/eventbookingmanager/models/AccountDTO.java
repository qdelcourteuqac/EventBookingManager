package main.java.eventbookingmanager.models;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class AccountDTO implements Serializable {

    @ApiModelProperty(value = "Email du compte", required = true)
    private String email;

    @ApiModelProperty(value = "Mot de passe du compte", required = true)
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
