package pl.pwr.cinematicketsystem.service;

import static java.util.Optional.ofNullable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pwr.cinematicketsystem.dto.SeatResponse;
import pl.pwr.cinematicketsystem.dto.TicketValidatorResponse;
import pl.pwr.cinematicketsystem.entity.Reservation;
import pl.pwr.cinematicketsystem.entity.Room;
import pl.pwr.cinematicketsystem.entity.Seat;
import pl.pwr.cinematicketsystem.entity.Show;
import pl.pwr.cinematicketsystem.entity.Ticket;
import pl.pwr.cinematicketsystem.entity.TicketState;
import pl.pwr.cinematicketsystem.repository.TicketRepository;

@Service
@RequiredArgsConstructor
public class TicketValidatorServiceImpl implements TicketValidatorService {

    private final TicketRepository ticketRepository;
    private final TicketService ticketService;
    private final ShowService showService;

    @Override
    public void scanTicket(String code) {
        Ticket ticket = ticketService.getTicketByCode(code);
        if (ticketService.isValid(ticket.getCode())) {
            ticket.setState(TicketState.USED);
            ticketRepository.save(ticket);
        }
    }

    @Override
    public TicketValidatorResponse getInfo(String code) {
        Ticket ticket = ticketService.getTicketByCode(code);
        ticketService.updateState(ticket);
        List<SeatResponse> seats = showService.getSeats(
            ticket.getShow().getId()
        );

        List<SeatResponse> reservedSeats = new java.util.ArrayList<>();

        Optional<List<Ticket>> tickets = ofNullable(ticket)
            .map(Ticket::getReservation)
            .map(Reservation::getTickets);

        Integer numberOfReservedSeats = tickets.map(List::size).orElse(0);

        tickets
            .map(List::stream)
            .orElseGet(Stream::empty)
            .map(Ticket::getSeat)
            .filter(seat -> seat != null)
            .forEach(seat -> {
                reservedSeats.add(
                    SeatResponse.builder()
                        .rowNumber(seat.getRowNumber())
                        .seatNumber(seat.getSeatNumber())
                        .roomId(
                            ofNullable(seat)
                                .map(Seat::getRoom)
                                .map(Room::getId)
                                .orElse(null)
                        )
                        .reserved(Boolean.TRUE)
                        .build()
                );
            });

        Integer roomId = ofNullable(ticket)
            .map(Ticket::getShow)
            .map(Show::getRoom)
            .map(Room::getId)
            .orElse(null);

        return TicketValidatorResponse.builder()
            .ticketCode(code)
            .ticketState(ticket.getState())
            .numberOfReservedSeats(numberOfReservedSeats)
            .showDate(ticket.getShow().getDate())
            .seats(seats)
            .roomId(roomId)
            .reservedSeats(reservedSeats)
            .build();
    }
}
