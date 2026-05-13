package pl.pwr.cinematicketsystem.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pwr.cinematicketsystem.dto.SeatResponse;
import pl.pwr.cinematicketsystem.dto.TicketValidatorResponse;
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
        Integer numberOfReservedSeats = 0;
        for (SeatResponse seat : seats) {
            if (seat.getReserved()) {
                numberOfReservedSeats += 1;
            }
        }

        return TicketValidatorResponse.builder()
            .ticketCode(code)
            .ticketState(ticket.getState())
            .numberOfReservedSeats(numberOfReservedSeats)
            .showDate(ticket.getShow().getDate())
            .seats(seats)
            .build();
    }
}
