package main.java.eventbookingmanager.models.response;

public class ScanResponse {

    protected String message;

    public ScanResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
