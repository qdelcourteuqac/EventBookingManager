package main.java.eventbookingmanager.models.response;

import main.java.eventbookingmanager.models.Scan;

public class ScanResponse {

    private Scan scan;
    private String message;

    public ScanResponse(Scan scan) {
        this.scan = scan;
    }

    public ScanResponse(String message) {
        this.message = message;
    }

    public Scan getScan() {
        return this.scan;
    }

    public String getMessage() {
        return this.message;
    }
}
