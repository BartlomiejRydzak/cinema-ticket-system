package pl.pwr.cinematicketsystem.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pwr.cinematicketsystem.dto.TicketValidatorRequest;
import pl.pwr.cinematicketsystem.entity.TicketValidator;
import pl.pwr.cinematicketsystem.repository.TicketValidatorRepository;
import pl.pwr.cinematicketsystem.service.TicketValidatorService;

@RestController
@RequestMapping("/ticket-validators")
public class TicketValidatorController {

    private TicketValidatorService ticketValidatorService;

    @Autowired
    public TicketValidatorController(TicketValidatorService ticketValidatorService) {
        this.ticketValidatorService = ticketValidatorService;
    }

    @PostMapping("/login")
    public TicketValidator login(@RequestBody TicketValidatorRequest ticketValidatorRequest){
        return ticketValidatorService.login(ticketValidatorRequest);
    }
}
