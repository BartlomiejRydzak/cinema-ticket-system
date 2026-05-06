package pl.pwr.cinematicketsystem.service;

import pl.pwr.cinematicketsystem.dto.ShowRequest;
import pl.pwr.cinematicketsystem.dto.ShowResponse;
import pl.pwr.cinematicketsystem.dto.ShowShortResponse;
import pl.pwr.cinematicketsystem.entity.Movie;
import pl.pwr.cinematicketsystem.entity.Show;

import java.util.List;

public interface ShowService {

    Show addShow(ShowRequest showRequest);

    List<ShowResponse> getAllShows();

    ShowResponse mapToResponse(Show show);

    ShowShortResponse mapToShortResponse(Show show);
}
