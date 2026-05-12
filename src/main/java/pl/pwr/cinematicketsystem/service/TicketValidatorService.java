package pl.pwr.cinematicketsystem.service;

import pl.pwr.cinematicketsystem.dto.TicketValidatorRequest;
import pl.pwr.cinematicketsystem.dto.TicketValidatorResponse;
import pl.pwr.cinematicketsystem.entity.TicketValidator;

public interface TicketValidatorService {
    void scanTicket(String code);
    TicketValidatorResponse getInfo(String ticketCode);
}
