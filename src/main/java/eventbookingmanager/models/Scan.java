package main.java.eventbookingmanager.models;

import org.joda.time.DateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Scan implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column()
    private DateTime scanAt;

    public Scan() {
        this.scanAt = new DateTime();
    }

    public Long getId() {
        return id;
    }

    public DateTime getScanAt() {
        return scanAt;
    }

    public void setScanAt(DateTime scanAt) {
        this.scanAt = scanAt;
    }
}
