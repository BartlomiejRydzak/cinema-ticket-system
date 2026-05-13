package pl.pwr.cinematicketsystem.service;

import pl.pwr.cinematicketsystem.dto.TicketValidatorResponse;

public interface TicketValidatorService {
    void scanTicket(String code);
    TicketValidatorResponse getInfo(String ticketCode);
}
