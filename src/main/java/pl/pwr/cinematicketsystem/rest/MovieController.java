package pl.pwr.cinematicketsystem.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.pwr.cinematicketsystem.dto.MovieRequest;
import pl.pwr.cinematicketsystem.dto.MovieResponse;
import pl.pwr.cinematicketsystem.entity.Movie;
import pl.pwr.cinematicketsystem.service.MovieService;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService){
        this.movieService = movieService;
    }

    @PostMapping
    public MovieResponse addMovie(@RequestBody MovieRequest movieRequest){
        return movieService.addMovie(movieRequest);
    }

    @Tag(name = "Viewer")
    @GetMapping
    public List<MovieResponse> getAllMovies(){
        return movieService.getAllMovies();
    }
}
