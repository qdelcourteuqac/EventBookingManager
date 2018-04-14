package main.java.eventbookingmanager.repository;

import main.java.eventbookingmanager.models.Reservation;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public interface ReservationRepository extends BaseRepository<Reservation> {
}
