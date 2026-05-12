package pl.pwr.cinematicketsystem.service;

import pl.pwr.cinematicketsystem.dto.TicketRequest;
import pl.pwr.cinematicketsystem.dto.TicketResponse;
import pl.pwr.cinematicketsystem.entity.Ticket;

import java.sql.SQLException;

public interface TicketService {

    TicketResponse buyTicket(TicketRequest ticketRequest) throws SQLException;

    TicketResponse mapToResponse(Ticket ticket);

    Boolean isValid(String code);

    void updateState(Ticket ticket);
}
