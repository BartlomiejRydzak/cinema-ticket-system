package pl.pwr.cinematicketsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pwr.cinematicketsystem.dto.SeatResponse;
import pl.pwr.cinematicketsystem.dto.ShowRequest;
import pl.pwr.cinematicketsystem.dto.ShowResponse;
import pl.pwr.cinematicketsystem.dto.ShowShortResponse;
import pl.pwr.cinematicketsystem.entity.*;
import pl.pwr.cinematicketsystem.repository.MovieRepository;
import pl.pwr.cinematicketsystem.repository.RoomRepository;
import pl.pwr.cinematicketsystem.repository.ShowRepository;
import pl.pwr.cinematicketsystem.repository.TicketRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowServiceImpl implements ShowService{

    private ShowRepository showRepository;
    private MovieRepository movieRepository;
    private RoomRepository roomRepository;
    private TicketRepository ticketRepository;

    @Autowired
    public ShowServiceImpl(ShowRepository showRepository, MovieRepository movieRepository, RoomRepository roomRepository, TicketRepository ticketRepository){
        this.showRepository = showRepository;
        this.movieRepository = movieRepository;
        this.roomRepository = roomRepository;
        this.ticketRepository = ticketRepository;
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

    @Override
    public List<SeatResponse> getSeats(Integer id) {
        Show show = showRepository.findById(id).orElseThrow(() -> new RuntimeException("Show not found"));

        Room room = show.getRoom();

        List<Ticket> tickets = ticketRepository.findByShowId(id);
        List<Seat> reservedSeats = tickets.stream().map(Ticket::getSeat).toList();
        return room.getSeats()
                .stream()
                .map(seat -> SeatResponse.builder()
                        .rowNumber(seat.getRowNumber())
                        .seatNumber(seat.getSeatNumber())
                        .roomId(seat.getRoom().getId())
                        .reserved(reservedSeats.contains(seat))
                        .build()
                )
                .toList();

    }

}
