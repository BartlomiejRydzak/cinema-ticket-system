package pl.pwr.cinematicketsystem.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.pwr.cinematicketsystem.dto.TicketRequest;
import pl.pwr.cinematicketsystem.dto.TicketResponse;
import pl.pwr.cinematicketsystem.entity.Ticket;
import pl.pwr.cinematicketsystem.service.TicketService;

import java.sql.SQLException;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @Tag(name = "Viewer")
    @PostMapping
    TicketResponse buyTicket(@RequestBody TicketRequest ticketRequest) throws SQLException {
        return ticketService.buyTicket(ticketRequest);
    }

    @Tag(name = "TicketValidator")
    @GetMapping("/valid")
    Boolean isValid(@RequestParam String code){
        return ticketService.isValid(code);
    }
}
