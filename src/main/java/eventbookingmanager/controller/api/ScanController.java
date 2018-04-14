package main.java.eventbookingmanager.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import main.java.eventbookingmanager.exception.ScanException;
import main.java.eventbookingmanager.models.Event;
import main.java.eventbookingmanager.models.Person;
import main.java.eventbookingmanager.models.Reservation;
import main.java.eventbookingmanager.models.Scan;
import main.java.eventbookingmanager.models.response.ScanProgress;
import main.java.eventbookingmanager.models.response.ScanResponse;
import main.java.eventbookingmanager.repository.EventRepository;
import main.java.eventbookingmanager.repository.ReservationRepository;
import main.java.eventbookingmanager.repository.ScanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = {"Scan"}, description ="Opérations concernant les scans")
public class ScanController extends BaseApiController<Scan> {

    @Autowired
    protected ScanRepository repository;

    @Autowired
    protected ReservationRepository reservationRepository;

    @Autowired
    protected EventRepository eventRepository;

    @ApiOperation(value = "Scanner un Qr CODE")
    @GetMapping(path = "/scan/reservation/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ScanResponse> scanReservation(@PathVariable long id) {
        try {
            Reservation reservation = this.reservationRepository.findOne(id);

            if (reservation == null) {
                throw new Exception();
            }

            Person scannedPerson = reservation.getPerson();

            if (scannedPerson == null) {
                throw new ScanException(ScanException.Type.UNKNOWN_PERSON);
            }

            // TODO: check person access : throw ACCESS DENIED error if false

            Scan scan = repository.findByReservation(reservation);

            if (scan != null) {
                throw new ScanException(ScanException.Type.ALREADY_SCANNED);
            }

        } catch (ScanException scanException) {
            ScanResponse scanResponse = new ScanResponse(scanException.getMessage());
            return new ResponseEntity<>(scanResponse, HttpStatus.BAD_REQUEST);
        } catch (Exception exception) {
            ScanResponse scanResponse = new ScanResponse(ScanException.Type.EXCEPTION.getMessage());
            return new ResponseEntity<>(scanResponse, HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Progression des scans pour un évènement")
    @GetMapping(path = "/scan/event/progress/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ScanProgress> progress(@PathVariable long id) {
        Event event = this.eventRepository.findOne(id);

        if (event == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        int totalOfAttendee = event.getReservations().size();
        int totalAlreadyScanned = this.repository.countDistinctByReservation_Event_Id(event.getId());

        return new ResponseEntity<>(new ScanProgress(totalOfAttendee,totalAlreadyScanned), HttpStatus.OK);
    }

}
