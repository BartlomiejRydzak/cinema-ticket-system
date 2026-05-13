package pl.pwr.cinematicketsystem.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import java.sql.SQLException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.pwr.cinematicketsystem.dto.TicketRequest;
import pl.pwr.cinematicketsystem.dto.TicketResponse;
import pl.pwr.cinematicketsystem.service.TicketService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;

    @Tag(name = "Viewer")
    @PostMapping
    TicketResponse buyTicket(@RequestBody TicketRequest ticketRequest)
        throws SQLException {
        return ticketService.buyTicket(ticketRequest);
    }

    @Tag(name = "TicketValidator")
    @GetMapping("/valid")
    Boolean isValid(@RequestParam String code) {
        return ticketService.isValid(code);
    }
}
