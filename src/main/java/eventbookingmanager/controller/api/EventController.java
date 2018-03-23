package main.java.eventbookingmanager.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import main.java.eventbookingmanager.models.Event;
import main.java.eventbookingmanager.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@Api(tags = {"Evènement"}, description ="Opérations concernant les évènements")
public class EventController extends BaseApiController<Event> {

    @Autowired
    protected BaseRepository<Event> repository;

    @ApiOperation(value = "Obtenir la liste de tous les évènements")
    @GetMapping(path = "/event", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Event> retrieveAllEvents() {
        return (List<Event>) repository.findAll();
    }

    @ApiOperation(value = "Obtenir un évènement")
    @GetMapping(path = "/event/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Event retrieveEvent(@PathVariable long id) {
        Optional<Event> event = Optional.ofNullable(repository.findOne(id));

        if (!event.isPresent())
            throw new RuntimeException("No event found for id-" + id);

        return event.get();
    }

    @ApiOperation(value = "Créer un évènement")
    @PostMapping(path = "/event", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Event> createStudent(@RequestBody Event event) {
        Event savedEvent = repository.save(event);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedEvent.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @ApiOperation(value = "Supprimer un évènement")
    @DeleteMapping(path = "/event/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteEvent(@PathVariable long id) {
        repository.delete(id);
    }

    @ApiOperation(value = "Modifier un évènement")
    @PutMapping(path = "/event/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Event> updateEvent(@RequestBody Event event, @PathVariable long id) {
        Optional<Event> eventOptional = Optional.ofNullable(repository.findOne(id));

        if (!eventOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        repository.save(event);

        return ResponseEntity.noContent().build();
    }
}
