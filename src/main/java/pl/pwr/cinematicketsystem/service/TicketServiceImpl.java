package pl.pwr.cinematicketsystem.service;

import static java.util.Optional.ofNullable;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import pl.pwr.cinematicketsystem.dto.TicketRequest;
import pl.pwr.cinematicketsystem.dto.TicketResponse;
import pl.pwr.cinematicketsystem.entity.Movie;
import pl.pwr.cinematicketsystem.entity.Show;
import pl.pwr.cinematicketsystem.entity.Ticket;
import pl.pwr.cinematicketsystem.entity.TicketState;
import pl.pwr.cinematicketsystem.exception.ResourceNotFoundException;
import pl.pwr.cinematicketsystem.repository.TicketRepository;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final ShowService showService;
    private final SeatService seatService;
    private final ReservationService reservationService;
    private final Clock clock;

    @Override
    public Ticket getTicketByCode(String code) {
        return ofNullable(ticketRepository.findByCode(code)).orElseThrow(() ->
            new ResourceNotFoundException("Ticket not found with code: " + code)
        );
    }

    @Override
    public TicketResponse buyTicket(TicketRequest ticketRequest) {
        try {
            Ticket ticket = Ticket.builder()
                .show(showService.getShowById(ticketRequest.getShowId()))
                .seat(seatService.getSeatById(ticketRequest.getSeatId()))
                .reservation(
                    reservationService.getReservationById(
                        ticketRequest.getReservationId()
                    )
                )
                .build();

            Ticket newTicket = ticketRepository.save(ticket);
            return mapToResponse(newTicket);
        } catch (DataIntegrityViolationException e) {
            // let DataIntegrityViolationException propagate — GlobalExceptionHandler maps it to 409
            throw e;
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
        Ticket ticket = getTicketByCode(code);
        updateState(ticket);
        return TicketState.VALID.equals(ticket.getState());
    }

    @Override
    public void updateState(Ticket ticket) {
        Show show = Optional.ofNullable(ticket)
            .map(Ticket::getShow)
            .orElseThrow(() -> new RuntimeException("Show not found"));

        Movie movie = Optional.ofNullable(show.getMovie()).orElseThrow(() ->
            new RuntimeException("Movie not found")
        );

        if (TicketState.USED.equals(ticket.getState())) {
            return; // used tickets remain used
        }

        LocalDateTime showTime = show.getDate();
        long duration = Optional.ofNullable(movie.getDurationMinutes()).orElse(
            0
        );
        LocalDateTime showEnd = showTime.plusMinutes(duration);
        LocalDateTime now = LocalDateTime.now(clock);

        TicketState newState;
        boolean withinWindow =
            !now.isBefore(showTime.minusMinutes(15)) && !now.isAfter(showEnd);
        if (withinWindow) {
            newState = TicketState.VALID;
        } else {
            newState = TicketState.INVALID;
        }

        if (!newState.equals(ticket.getState())) {
            ticket.setState(newState);
            ticketRepository.save(ticket);
        }
    }
}
