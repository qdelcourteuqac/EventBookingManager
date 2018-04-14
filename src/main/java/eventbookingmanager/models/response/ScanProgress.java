package main.java.eventbookingmanager.models.response;

public class ScanProgress {

    private int totalOfAttendee;
    private int totalAlreadyScanned;

    public ScanProgress(int totalOfAttendee, int totalAlreadyScanned) {
        this.totalOfAttendee = totalOfAttendee;
        this.totalAlreadyScanned = totalAlreadyScanned;
    }

    public int getTotalOfAttendee() {
        return totalOfAttendee;
    }

    public int getTotalAlreadyScanned() {
        return totalAlreadyScanned;
    }
}
