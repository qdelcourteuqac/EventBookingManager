package main.java.eventbookingmanager.models;

import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Scan extends BaseEntity {

    @Column()
    private DateTime scanAt;

    @OneToOne()
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

    public Reservation getReservation() {
        return this.reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}
