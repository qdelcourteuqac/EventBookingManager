package main.java.eventbookingmanager.repository;

import main.java.eventbookingmanager.models.Reservation;
import main.java.eventbookingmanager.models.Scan;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public interface ScanRepository extends BaseRepository<Scan> {
    Scan findByReservation(Reservation reservation);

    int countDistinctByReservation_Event_Id(long id);
}
