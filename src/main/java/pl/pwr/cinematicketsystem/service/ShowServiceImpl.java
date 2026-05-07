package pl.pwr.cinematicketsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pwr.cinematicketsystem.dto.ShowRequest;
import pl.pwr.cinematicketsystem.dto.ShowResponse;
import pl.pwr.cinematicketsystem.dto.ShowShortResponse;
import pl.pwr.cinematicketsystem.entity.Movie;
import pl.pwr.cinematicketsystem.entity.Show;
import pl.pwr.cinematicketsystem.repository.MovieRepository;
import pl.pwr.cinematicketsystem.repository.RoomRepository;
import pl.pwr.cinematicketsystem.repository.ShowRepository;

import java.util.List;

@Service
public class ShowServiceImpl implements ShowService{

    private ShowRepository showRepository;
    private MovieRepository movieRepository;
    private RoomRepository roomRepository;

    @Autowired
    public ShowServiceImpl(ShowRepository showRepository, MovieRepository movieRepository, RoomRepository roomRepository){
        this.showRepository = showRepository;
        this.movieRepository = movieRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    public ShowResponse addShow(ShowRequest showRequest) {
        Show show = new Show();

        show.setDate(showRequest.getDate());
        show.setRoom(roomRepository.findById(showRequest.getRoomId()).orElseThrow(() -> new RuntimeException("Room not found")));
        show.setMovie(movieRepository.findById(showRequest.getMovieId()).orElseThrow(() -> new RuntimeException("Movie not found")));
        Show newShow = showRepository.save(show);
        return mapToResponse(newShow);
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
