package pl.pwr.cinematicketsystem.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.pwr.cinematicketsystem.dto.SeatResponse;
import pl.pwr.cinematicketsystem.dto.ShowRequest;
import pl.pwr.cinematicketsystem.dto.ShowResponse;
import pl.pwr.cinematicketsystem.service.ShowService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/shows")
public class ShowController {

    private final ShowService showService;

    @PostMapping
    public ShowResponse addShow(@RequestBody ShowRequest showRequest) {
        return showService.addShow(showRequest);
    }

    @Tag(name = "Viewer")
    @GetMapping
    public List<ShowResponse> getAllShows() {
        return showService.getAllShows();
    }

    @Tag(name = "TicketValidator")
    @GetMapping("/{id}/seats")
    public List<SeatResponse> getSeats(@PathVariable Integer id) {
        return showService.getSeats(id);
    }
}
