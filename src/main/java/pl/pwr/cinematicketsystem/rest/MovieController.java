package pl.pwr.cinematicketsystem.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.pwr.cinematicketsystem.dto.MovieRequest;
import pl.pwr.cinematicketsystem.dto.MovieResponse;
import pl.pwr.cinematicketsystem.service.MovieService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    @PostMapping
    public MovieResponse addMovie(@RequestBody MovieRequest movieRequest) {
        return movieService.addMovie(movieRequest);
    }

    @Tag(name = "Viewer")
    @GetMapping
    public List<MovieResponse> getAllMovies() {
        return movieService.getAllMovies();
    }
}
