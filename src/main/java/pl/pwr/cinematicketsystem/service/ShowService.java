package pl.pwr.cinematicketsystem.service;

import java.util.List;
import pl.pwr.cinematicketsystem.dto.SeatResponse;
import pl.pwr.cinematicketsystem.dto.ShowRequest;
import pl.pwr.cinematicketsystem.dto.ShowResponse;
import pl.pwr.cinematicketsystem.entity.Show;

public interface ShowService {
    ShowResponse addShow(ShowRequest showRequest);

    Show getShowById(Integer id);

    List<ShowResponse> getAllShows();

    ShowResponse mapToResponse(Show show);

    List<SeatResponse> getSeats(Integer id);
}
