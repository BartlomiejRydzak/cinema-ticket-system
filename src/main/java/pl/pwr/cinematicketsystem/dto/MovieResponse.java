package pl.pwr.cinematicketsystem.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MovieResponse {

    private Integer id;
    private String title;
    private String description;
    private Integer durationMinutes;
    private String imageUrl;
    private List<ShowShortResponse> shows;
}
