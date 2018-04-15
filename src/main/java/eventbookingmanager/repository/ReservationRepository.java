package main.java.eventbookingmanager.repository;

import main.java.eventbookingmanager.models.Event;
import main.java.eventbookingmanager.models.Reservation;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Primary
public interface ReservationRepository extends BaseRepository<Reservation> {
    List<Reservation> getAllByEvent(Event event);
}
