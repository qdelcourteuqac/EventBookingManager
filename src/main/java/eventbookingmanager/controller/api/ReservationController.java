package main.java.eventbookingmanager.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import main.java.eventbookingmanager.models.Event;
import main.java.eventbookingmanager.models.Person;
import main.java.eventbookingmanager.models.Reservation;
import main.java.eventbookingmanager.repository.BaseRepository;
import main.java.eventbookingmanager.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@Api(tags = {"Réservation"}, description = "Opérations concernant les réservations")
public class ReservationController extends BaseApiController<Reservation> {

    @Autowired
    protected BaseRepository<Reservation> repository;

    @Autowired
    protected PersonRepository personRepository;

    @ApiOperation(value = "Obtenir la liste de toutes les réservations")
    @GetMapping(path = "/reservation", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Reservation> retrieveAllReservations() {
        return (List<Reservation>) repository.findAll();
    }

    @ApiOperation(value = "Obtenir les informations d'une réservation")
    @GetMapping(path = "/reservation/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Reservation> retrievePerson(@PathVariable long id) {
        Optional<Reservation> reservation = Optional.ofNullable(repository.findOne(id));

        if (!reservation.isPresent())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(reservation.get(), HttpStatus.OK);
    }

    @Transactional
    @ApiOperation(value = "Créer une réservation")
    @PostMapping(path = "/reservation", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) {
        // check if there is still places
        Event event = reservation.getEvent();
        if (event.getReservations().size() >= event.getMaxPlaces()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (reservation.getPerson().getId() == null) {
            Person person = this.personRepository.findPersonByFirstnameAndLastname(
                    reservation.getPerson().getFirstname(),
                    reservation.getPerson().getLastname()
            );

            if (person == null) {
                entityManager.persist(reservation.getPerson());
            } else {
                reservation.setPerson(person);
            }
        }

        Reservation savedReservation = repository.save(reservation);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedReservation.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @ApiOperation(value = "Supprimer une réservation")
    @DeleteMapping(path = "/reservation/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteReservation(@PathVariable long id) {
        repository.delete(id);
    }
}
