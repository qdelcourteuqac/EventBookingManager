package main.java.eventbookingmanager.models;

import io.swagger.annotations.ApiModelProperty;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.List;

@Entity
public class Event extends BaseEntity {

    @Column()
    @ApiModelProperty(notes = "Le nom de l'évènement", required = true)
    private String name;

    @Column()
    private DateTime createdAt;

    @Column()
    @ApiModelProperty(notes = "La date de début de l'évènement")
    private DateTime startAt;

    @Column()
    @ApiModelProperty(notes = "La date de fin de l'évènement")
    private DateTime endAt;

    @Column()
    @ApiModelProperty(notes = "Le nombre maximal de personne autorisé")
    private Integer maxPlaces;

    @OneToMany(mappedBy = "event", cascade = CascadeType.REMOVE)
    private List<Reservation> reservations;

    public Event() {
        this.createdAt = new DateTime();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(DateTime createdAt) {
        this.createdAt = createdAt;
    }

    public DateTime getStartAt() {
        return startAt;
    }

    public void setStartAt(DateTime startAt) {
        this.startAt = startAt;
    }

    public DateTime getEndAt() {
        return endAt;
    }

    public void setEndAt(DateTime endAt) {
        this.endAt = endAt;
    }

    public Integer getMaxPlaces() {
        return maxPlaces;
    }

    public void setMaxPlaces(Integer maxPlaces) {
        this.maxPlaces = maxPlaces;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
