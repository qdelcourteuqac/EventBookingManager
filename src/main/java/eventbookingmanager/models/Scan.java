package main.java.eventbookingmanager.models;

import org.joda.time.DateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Scan extends BaseEntity {

    @Column()
    private DateTime scanAt;

    // Le compte de la personne qui a fait le scan
    private Account scanner;

    // La reservation qui a été scannée
    private Reservation reservation;

    public Scan() {
        this.scanAt = new DateTime();
    }

    public DateTime getScanAt() {
        return scanAt;
    }

    public void setScanAt(DateTime scanAt) {
        this.scanAt = scanAt;
    }
}
