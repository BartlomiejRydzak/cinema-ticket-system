package pl.pwr.cinematicketsystem.service;

import pl.pwr.cinematicketsystem.dto.MovieRequest;
import pl.pwr.cinematicketsystem.dto.MovieResponse;
import pl.pwr.cinematicketsystem.entity.Movie;

import java.util.List;

public interface MovieService {

    Movie addMovie(MovieRequest movieRequest);

    List<MovieResponse> getAllMovies();

    MovieResponse mapToResponse(Movie movie);
}
