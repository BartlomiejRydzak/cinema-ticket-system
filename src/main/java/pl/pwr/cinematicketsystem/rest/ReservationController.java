package pl.pwr.cinematicketsystem.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pwr.cinematicketsystem.dto.ReservationRequest;
import pl.pwr.cinematicketsystem.dto.ReservationResponse;
import pl.pwr.cinematicketsystem.entity.Reservation;
import pl.pwr.cinematicketsystem.service.ReservationService;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @Tag(name = "Viewer")
    @PostMapping
    ReservationResponse addReservation(@RequestBody ReservationRequest reservationRequest){
        return reservationService.addReservation(reservationRequest);
    }
}
