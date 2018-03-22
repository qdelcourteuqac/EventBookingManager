package main.java.eventbookingmanager.models;

import org.joda.time.DateTime;

import java.io.Serializable;

public class Reservation implements Serializable {

    private DateTime reservedAt;

    public Reservation() {
        this.reservedAt = new DateTime();
    }

    public DateTime getReservedAt() {
        return reservedAt;
    }

    public void setReservedAt(DateTime reservedAt) {
        this.reservedAt = reservedAt;
    }
}
