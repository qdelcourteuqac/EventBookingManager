package main.java.eventbookingmanager.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import main.java.eventbookingmanager.models.Reservation;
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
@Api(tags = {"Réservation"}, description = "Opérations concernant les réservations")
public class ReservationController extends BaseApiController<Reservation> {

    @Autowired
    protected BaseRepository<Reservation> repository;

    @ApiOperation(value = "Obtenir la liste de toutes les réservations")
    @GetMapping(path = "/reservation", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Reservation> retrieveAllReservations() {
        return (List<Reservation>) repository.findAll();
    }

    @ApiOperation(value = "Obtenir les informations d'une réservation")
    @GetMapping(path = "/reservation/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Reservation retrievePerson(@PathVariable long id) {
        Optional<Reservation> reservation = Optional.ofNullable(repository.findOne(id));

        if (!reservation.isPresent())
            throw new RuntimeException("No reservation found for id-" + id);

        return reservation.get();
    }

    @ApiOperation(value = "Créer une réservation")
    @PostMapping(path = "/reservation", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createStudent(@RequestBody Reservation reservation) {
        Reservation savedReservation = repository.save(reservation);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedReservation.getId()).toUri();

        return ResponseEntity.created(location).build();
    }
}
