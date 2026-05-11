package pl.pwr.cinematicketsystem.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.pwr.cinematicketsystem.dto.TicketValidatorRequest;
import pl.pwr.cinematicketsystem.dto.TicketValidatorResponse;
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

    @PostMapping("/scan-ticket")
    public void scanTicket(String code){
        ticketValidatorService.scanTicket(code);
    }

    @GetMapping("/info")
    public TicketValidatorResponse getInfo(String code){
        return ticketValidatorService.getInfo(code);
    }
}
