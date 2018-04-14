package main.java.eventbookingmanager.models.response;

import main.java.eventbookingmanager.models.Reservation;

public class ScanResponse {

    private Reservation reservation;
    private String message;

    public ScanResponse(Reservation reservation) {
        this.reservation = reservation;
        this.message = null;
    }

    public ScanResponse(String message) {
        this.message = message;
        this.reservation = null;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public String getMessage() {
        return this.message;
    }
}
