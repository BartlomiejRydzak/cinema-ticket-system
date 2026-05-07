package pl.pwr.cinematicketsystem.service;

import pl.pwr.cinematicketsystem.dto.TicketRequest;
import pl.pwr.cinematicketsystem.dto.TicketResponse;
import pl.pwr.cinematicketsystem.entity.Ticket;

public interface TicketService {

    TicketResponse buyTicket(TicketRequest ticketRequest);

    TicketResponse mapToResponse(Ticket ticket);
}
