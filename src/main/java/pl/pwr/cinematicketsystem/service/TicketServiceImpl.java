package pl.pwr.cinematicketsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import pl.pwr.cinematicketsystem.dto.TicketRequest;
import pl.pwr.cinematicketsystem.dto.TicketResponse;
import pl.pwr.cinematicketsystem.entity.Ticket;
import pl.pwr.cinematicketsystem.entity.TicketState;
import pl.pwr.cinematicketsystem.repository.ReservationRepository;
import pl.pwr.cinematicketsystem.repository.SeatRepository;
import pl.pwr.cinematicketsystem.repository.ShowRepository;
import pl.pwr.cinematicketsystem.repository.TicketRepository;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService{

    private TicketRepository ticketRepository;
    private ShowRepository showRepository;
    private SeatRepository seatRepository;
    private ReservationRepository reservationRepository;

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository, ShowRepository showRepository, SeatRepository seatRepository, ReservationRepository reservationRepository) {
        this.ticketRepository = ticketRepository;
        this.showRepository = showRepository;
        this.seatRepository = seatRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public TicketResponse buyTicket(TicketRequest ticketRequest){
        try {
            Ticket ticket = new Ticket();

            ticket.setShow(showRepository.findById(ticketRequest.getShowId()).orElseThrow(() -> new RuntimeException("Show not found")));
            ticket.setSeat(seatRepository.findById(ticketRequest.getSeatId()).orElseThrow(() -> new RuntimeException("Seat not found")));
            ticket.setReservation(reservationRepository.findById(ticketRequest.getReservationId()).orElseThrow(() -> new RuntimeException("Reservation not found")));

            Ticket newTicket = ticketRepository.save(ticket);
            return mapToResponse(newTicket);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Seat is already taken");
        }

    }

    @Override
    public TicketResponse mapToResponse(Ticket ticket) {
        return TicketResponse.builder()
                .id(ticket.getId())
                .showId(ticket.getShow().getId())
                .seatId(ticket.getSeat().getId())
                .reservationId(ticket.getReservation().getId())
                .build();
    }

    @Override
    public Boolean isValid(String code) {
        Ticket ticket = ticketRepository.findByCode(code);
        updateState(ticket);
        return ticket.getState().equals(TicketState.VALID);
    }

    public void updateState(Ticket ticket){
        if (!ticket.getState().equals(TicketState.USED)) {
            LocalDateTime showTime = ticket.getShow().getDate();
            LocalDateTime now = LocalDateTime.now();
            if (now.isBefore(showTime) && now.isAfter(showTime.minusMinutes(15))) {
                ticket.setState(TicketState.VALID);
            } else if (now.isAfter(showTime)) {
                ticket.setState(TicketState.INVALID);
            }
        }
    }
}
