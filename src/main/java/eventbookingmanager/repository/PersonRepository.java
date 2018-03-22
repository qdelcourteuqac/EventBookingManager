package main.java.eventbookingmanager.repository;

import main.java.eventbookingmanager.models.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long>{
}
