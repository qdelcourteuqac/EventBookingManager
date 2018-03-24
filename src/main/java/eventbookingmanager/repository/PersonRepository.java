package main.java.eventbookingmanager.repository;

import main.java.eventbookingmanager.models.Person;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public interface PersonRepository extends BaseRepository<Person> {
}
