package main.java.eventbookingmanager.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import main.java.eventbookingmanager.models.Person;
import main.java.eventbookingmanager.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@Api(tags = {"Personne"}, description = "Opérations concernant les personnes")
public class PersonController extends BaseApiController<Person> {

    @Autowired
    protected PersonRepository repository;

    @ApiOperation(value = "Obtenir la liste de toutes les personnes ayant réservé")
    @GetMapping(path = "/person", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Person> retrieveAllPersons() {
        return (List<Person>) repository.findAll();
    }

    @ApiOperation(value = "Obtenir les informations d'une personne")
    @GetMapping(path = "/person/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Person retrievePerson(@PathVariable long id) {
        Optional<Person> person = Optional.ofNullable(repository.findOne(id));

        if (!person.isPresent())
            throw new RuntimeException("No person found for id-" + id);

        return person.get();
    }

    @ApiOperation(value = "Créer une personne")
    @PostMapping(path = "/person", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        Person savedPerson = repository.save(person);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedPerson.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @ApiOperation(value = "Supprimer une personne")
    @DeleteMapping(path = "/person/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteEvent(@PathVariable long id) {
        repository.delete(id);
    }

    @ApiOperation(value = "Modifier une les informations d'une personne")
    @PutMapping(path = "/person/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> updateEvent(@RequestBody Person person, @PathVariable long id) {
        Optional<Person> personOptional = Optional.ofNullable(repository.findOne(id));

        if (!personOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        repository.save(person);

        return ResponseEntity.noContent().build();
    }
}
