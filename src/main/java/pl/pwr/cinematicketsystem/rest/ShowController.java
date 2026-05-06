package pl.pwr.cinematicketsystem.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.pwr.cinematicketsystem.dto.ShowRequest;
import pl.pwr.cinematicketsystem.dto.ShowResponse;
import pl.pwr.cinematicketsystem.entity.Movie;
import pl.pwr.cinematicketsystem.entity.Show;
import pl.pwr.cinematicketsystem.service.ShowService;

import java.util.List;

@RestController
@RequestMapping("/shows")
public class ShowController {

    private ShowService showService;

    @Autowired
    public ShowController(ShowService showService){
        this.showService = showService;
    }

    @PostMapping
    public Show addShow(@RequestBody ShowRequest showRequest){
        return showService.addShow(showRequest);
    }

    @GetMapping
    public List<ShowResponse> getAllShows(){
        return showService.getAllShows();
    }
}
