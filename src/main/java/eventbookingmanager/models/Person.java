package main.java.eventbookingmanager.models;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Person extends BaseEntity {

    @Column()
    @ApiModelProperty(notes = "Le prénom de la personne", required = true)
    private String firstname;

    @Column()
    @ApiModelProperty(notes = "Le nom de famille de la personne", required = true)
    private String  lastname;

    @Column()
    @ApiModelProperty(notes = "L'email de la personne", required = true)
    private String email;

    @Column()
    @ApiModelProperty(notes = "Le numéro de téléphone de la personne")
    private String phoneNumber;

    @Column()
    @ApiModelProperty(notes = "La date de naissance de la personne")
    private Date birthdate;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }
}
