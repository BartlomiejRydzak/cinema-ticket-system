package pl.pwr.cinematicketsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pwr.cinematicketsystem.dto.ShowRequest;
import pl.pwr.cinematicketsystem.dto.ShowResponse;
import pl.pwr.cinematicketsystem.dto.ShowShortResponse;
import pl.pwr.cinematicketsystem.entity.Movie;
import pl.pwr.cinematicketsystem.entity.Show;
import pl.pwr.cinematicketsystem.repository.MovieRepository;
import pl.pwr.cinematicketsystem.repository.ShowRepository;

import java.util.List;

@Service
public class ShowServiceImpl implements ShowService{

    private ShowRepository showRepository;
    private MovieRepository movieRepository;

    @Autowired
    public ShowServiceImpl(ShowRepository showRepository, MovieRepository movieRepository){
        this.showRepository = showRepository;
        this.movieRepository = movieRepository;
    }

    @Override
    public Show addShow(ShowRequest showRequest) {
        Show show = new Show();

        show.setDate(showRequest.getDate());
        show.setRoom(showRequest.getRoom());

        show.setMovie(
                movieRepository.findById(showRequest.getMovieId())
                        .orElseThrow(() -> new RuntimeException("Movie not found"))
        );

        return showRepository.save(show);
    }

    @Override
    public List<ShowResponse> getAllShows() {
        return showRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public ShowResponse mapToResponse(Show show) {
        return ShowResponse.builder()
                .date(show.getDate())
                .durationMinutes(show.getMovie().getDurationMinutes())
                .movieTitle(show.getMovie().getTitle())
                .description(show.getMovie().getDescription())
                .imgUrl(show.getMovie().getImageUrl())
                .build();
    }

}
