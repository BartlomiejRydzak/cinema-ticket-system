package pl.pwr.cinematicketsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pwr.cinematicketsystem.dto.TicketRequest;
import pl.pwr.cinematicketsystem.dto.TicketResponse;
import pl.pwr.cinematicketsystem.entity.Ticket;
import pl.pwr.cinematicketsystem.repository.SeatRepository;
import pl.pwr.cinematicketsystem.repository.ShowRepository;
import pl.pwr.cinematicketsystem.repository.TicketRepository;

@Service
public class TicketServiceImpl implements TicketService{

    private TicketRepository ticketRepository;
    private ShowRepository showRepository;
    private SeatRepository seatRepository;

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository, ShowRepository showRepository, SeatRepository seatRepository) {
        this.ticketRepository = ticketRepository;
        this.showRepository = showRepository;
        this.seatRepository = seatRepository;
    }

    @Override
    public TicketResponse buyTicket(TicketRequest ticketRequest) {
        Ticket ticket = new Ticket();

        ticket.setShow(showRepository.findById(ticketRequest.getShowId()).orElseThrow(() -> new RuntimeException("Show not found")));
        ticket.setSeat(seatRepository.findById(ticketRequest.getSeatId()).orElseThrow(() -> new RuntimeException("Seat not found")));
        Ticket newTicket = ticketRepository.save(ticket);
        return mapToResponse(newTicket);
    }

    @Override
    public TicketResponse mapToResponse(Ticket ticket) {
        return TicketResponse.builder()
                .id(ticket.getId())
                .showId(ticket.getShow().getId())
                .seatId(ticket.getSeat().getId())
                .build();
    }
}
