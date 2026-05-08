package pl.pwr.cinematicketsystem.service;

import pl.pwr.cinematicketsystem.dto.ReservationRequest;
import pl.pwr.cinematicketsystem.dto.ReservationResponse;
import pl.pwr.cinematicketsystem.entity.Reservation;

public interface ReservationService {
    ReservationResponse addReservation(ReservationRequest reservationRequest);

}
