package pl.pwr.cinematicketsystem.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
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

    @Tag(name = "TicketValidator")
    @PostMapping("/login")
    public TicketValidator login(@RequestBody TicketValidatorRequest ticketValidatorRequest){
        return ticketValidatorService.login(ticketValidatorRequest);
    }

    @Tag(name = "TicketValidator")
    @PostMapping("/scan-ticket")
    public void scanTicket(String code){
        ticketValidatorService.scanTicket(code);
    }

    @Tag(name = "TicketValidator")
    @GetMapping("/info")
    public TicketValidatorResponse getInfo(String code){
        return ticketValidatorService.getInfo(code);
    }
}
