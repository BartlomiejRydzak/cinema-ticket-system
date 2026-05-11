package pl.pwr.cinematicketsystem.service;

import pl.pwr.cinematicketsystem.dto.TicketValidatorRequest;
import pl.pwr.cinematicketsystem.entity.TicketValidator;

public interface TicketValidatorService {
    TicketValidator login(TicketValidatorRequest ticketValidatorRequest);
}
