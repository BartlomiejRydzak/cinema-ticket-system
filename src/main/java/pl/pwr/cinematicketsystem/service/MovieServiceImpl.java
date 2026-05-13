package pl.pwr.cinematicketsystem.service;

import static java.util.Optional.ofNullable;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pwr.cinematicketsystem.dto.MovieRequest;
import pl.pwr.cinematicketsystem.dto.MovieResponse;
import pl.pwr.cinematicketsystem.dto.ShowShortResponse;
import pl.pwr.cinematicketsystem.entity.Movie;
import pl.pwr.cinematicketsystem.exception.ResourceNotFoundException;
import pl.pwr.cinematicketsystem.repository.MovieRepository;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    @Override
    public Movie getMovieById(Integer id) {
        return movieRepository
            .findById(id)
            .orElseThrow(() ->
                new ResourceNotFoundException("Movie not found with id: " + id)
            );
    }

    @Override
    public MovieResponse addMovie(MovieRequest movieRequest) {
        Movie movie = Movie.builder()
            .title(movieRequest.getTitle())
            .description(movieRequest.getDescription())
            .imageUrl(movieRequest.getImageUrl())
            .durationMinutes(movieRequest.getDurationMinutes())
            .build();
        Movie newMovie = movieRepository.save(movie);
        return mapToResponse(newMovie);
    }

    @Override
    public List<MovieResponse> getAllMovies() {
        return movieRepository
            .findAll()
            .stream()
            .map(this::mapToResponse)
            .toList();
    }

    @Override
    public MovieResponse mapToResponse(Movie movie) {
        return MovieResponse.builder()
            .id(movie.getId())
            .title(movie.getTitle())
            .description(movie.getDescription())
            .durationMinutes(movie.getDurationMinutes())
            .imageUrl(movie.getImageUrl())
            .shows(getShows(movie))
            .build();
    }

    private List<ShowShortResponse> getShows(Movie movie) {
        return ofNullable(movie.getShows())
            .orElse(List.of())
            .stream()
            .map(show ->
                ShowShortResponse.builder()
                    .date(show.getDate())
                    .roomId(show.getRoom().getId())
                    .build()
            )
            .toList();
    }
}
