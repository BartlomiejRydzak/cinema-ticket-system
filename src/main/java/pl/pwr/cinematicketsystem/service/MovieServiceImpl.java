package pl.pwr.cinematicketsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pwr.cinematicketsystem.dto.MovieRequest;
import pl.pwr.cinematicketsystem.dto.MovieResponse;
import pl.pwr.cinematicketsystem.dto.ShowShortResponse;
import pl.pwr.cinematicketsystem.entity.Movie;
import pl.pwr.cinematicketsystem.repository.MovieRepository;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService{

    private MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository){
        this.movieRepository = movieRepository;
    }

    @Override
    public MovieResponse addMovie(MovieRequest movieRequest) {
        Movie movie = new Movie();

        movie.setTitle(movieRequest.getTitle());
        movie.setDescription(movieRequest.getDescription());
        movie.setImageUrl(movieRequest.getImageUrl());
        movie.setDurationMinutes(movieRequest.getDurationMinutes());
        Movie newMovie = movieRepository.save(movie);
        return mapToResponse(newMovie);
    }

    private Integer id;
    private String title;
    private String description;
    private Integer durationMinutes;
    private String imageUrl;
    private List<ShowShortResponse> shows;

    @Override
    public List<MovieResponse> getAllMovies() {
        return movieRepository.findAll()
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
                .shows(
                        movie.getShows() == null ? List.of() :
                        movie.getShows().stream()
                                .map(show -> ShowShortResponse.builder()
                                        .date(show.getDate())
                                        .roomId(show.getRoom().getId())
                                        .build())
                                .toList()
                )
                .build();
    }
}
