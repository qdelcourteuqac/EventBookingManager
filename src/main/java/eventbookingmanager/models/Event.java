package main.java.eventbookingmanager.models;

import io.swagger.annotations.ApiModelProperty;
import org.joda.time.DateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Event implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

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

    public Event() {
        this.createdAt = new DateTime();
    }

    public Long getId() {
        return this.id;
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
}
