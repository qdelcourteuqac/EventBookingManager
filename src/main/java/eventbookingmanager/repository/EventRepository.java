package main.java.eventbookingmanager.repository;

import main.java.eventbookingmanager.models.Event;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public interface EventRepository extends BaseRepository<Event> {
}
