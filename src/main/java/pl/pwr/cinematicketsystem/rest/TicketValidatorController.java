package pl.pwr.cinematicketsystem.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.pwr.cinematicketsystem.dto.TicketValidatorResponse;
import pl.pwr.cinematicketsystem.service.TicketValidatorService;

@RestController
@RequestMapping("/ticket-validators")
@RequiredArgsConstructor
public class TicketValidatorController {

    private final TicketValidatorService ticketValidatorService;

    @Tag(name = "TicketValidator")
    @PostMapping("/scan-ticket")
    public void scanTicket(@RequestParam String code) {
        ticketValidatorService.scanTicket(code);
    }

    @Tag(name = "TicketValidator")
    @GetMapping("/info")
    public TicketValidatorResponse getInfo(@RequestParam String code) {
        return ticketValidatorService.getInfo(code);
    }
}
