package pl.pwr.cinematicketsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pwr.cinematicketsystem.dto.ReservationRequest;
import pl.pwr.cinematicketsystem.dto.ReservationResponse;
import pl.pwr.cinematicketsystem.entity.Reservation;
import pl.pwr.cinematicketsystem.entity.Show;
import pl.pwr.cinematicketsystem.entity.Ticket;
import pl.pwr.cinematicketsystem.entity.TicketState;
import pl.pwr.cinematicketsystem.repository.ReservationRepository;
import pl.pwr.cinematicketsystem.repository.SeatRepository;
import pl.pwr.cinematicketsystem.repository.ShowRepository;
import pl.pwr.cinematicketsystem.repository.TicketRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ReservationServiceImpl implements ReservationService{

    private ReservationRepository reservationRepository;
    private TicketRepository ticketRepository;
    private SeatRepository seatRepository;
    private ShowRepository showRepository;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository, TicketRepository ticketRepository, SeatRepository seatRepository, ShowRepository showRepository) {
        this.reservationRepository = reservationRepository;
        this.ticketRepository = ticketRepository;
        this.seatRepository = seatRepository;
        this.showRepository = showRepository;
    }

    @Override
    public ReservationResponse addReservation(ReservationRequest reservationRequest) {
        Reservation reservation = new Reservation();
        String code = "RES-" + UUID.randomUUID().toString().substring(0, 8);
        reservation.setCode(code);

        Show show = showRepository.findById(reservationRequest.getShowId()).orElseThrow(() -> new RuntimeException("Show not found"));

        List<Ticket> tickets = new ArrayList<>();

        for(Integer id : reservationRequest.getSeatIds()){
            Ticket ticket = new Ticket();
            ticket.setSeat(seatRepository.findById(id).orElseThrow(() -> new RuntimeException("Seat not found")));
            ticket.setShow(show);
            ticket.setReservation(reservation);
            ticket.setState(TicketState.INVALID);

            reservation.addTicket(ticket);
        }


        Reservation newReservation = reservationRepository.save(reservation);


        return mapToResponse(newReservation);
    }

    public ReservationResponse mapToResponse(Reservation reservation){
        return ReservationResponse.builder()
                .id(reservation.getId())
                .code(reservation.getCode())
                .build();
    }
}
