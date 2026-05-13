package pl.pwr.cinematicketsystem.service;

import java.util.List;
import pl.pwr.cinematicketsystem.dto.MovieRequest;
import pl.pwr.cinematicketsystem.dto.MovieResponse;
import pl.pwr.cinematicketsystem.entity.Movie;

public interface MovieService {
    Movie getMovieById(Integer id);

    MovieResponse addMovie(MovieRequest movieRequest);

    List<MovieResponse> getAllMovies();

    MovieResponse mapToResponse(Movie movie);
}
