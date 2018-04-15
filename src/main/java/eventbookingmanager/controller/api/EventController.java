package main.java.eventbookingmanager.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import main.java.eventbookingmanager.models.Event;
import main.java.eventbookingmanager.models.Reservation;
import main.java.eventbookingmanager.repository.EventRepository;
import main.java.eventbookingmanager.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    protected EventRepository repository;

    @Autowired
    protected ReservationRepository reservationRepository;

    @ApiOperation(value = "Obtenir la liste de tous les évènements")
    @GetMapping(path = "/event", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Event> retrieveAllEvents() {
        return (List<Event>) repository.findAll();
    }

    @ApiOperation(value = "Obtenir un évènement")
    @GetMapping(path = "/event/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Event> retrieveEvent(@PathVariable long id) {
        Optional<Event> event = Optional.ofNullable(repository.findOne(id));

        if (!event.isPresent())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(event.get(), HttpStatus.OK);
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

    @ApiOperation(value = "Récupérer les réservations d'un évènement")
    @GetMapping(path = "/event/reservations/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Reservation>> retrieveReservationsForAnEvent(@PathVariable long id) {
        Event event = repository.findOne(id);

        if (event == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(reservationRepository.getAllByEvent(event), HttpStatus.OK);
    }

    @ApiOperation(value = "Rechercher un évènement avec son nom")
    @GetMapping(path = "/event/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Event>> searchByName(@RequestParam(name = "name") String name) {
        List<Event> events = repository.findAllByNameContaining(name);

        return new ResponseEntity<>(events, HttpStatus.OK);
    }
}
