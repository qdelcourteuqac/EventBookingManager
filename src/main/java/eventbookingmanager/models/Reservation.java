package main.java.eventbookingmanager.models;

import io.swagger.annotations.ApiModelProperty;
import org.joda.time.DateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Entity
public class Reservation extends BaseEntity {

    @Column()
    private DateTime reservedAt;

    @OneToOne()
    @ApiModelProperty(notes = "La personne ayant réservé", required = true)
    private Person person;

    @ManyToOne()
    @ApiModelProperty(notes = "L'évènement de réservation", required = true)
    private Event event;

    public Reservation() {
        this.reservedAt = new DateTime();
    }

    public DateTime getReservedAt() {
        return reservedAt;
    }

    public void setReservedAt(DateTime reservedAt) {
        this.reservedAt = reservedAt;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
