package pl.pwr.cinematicketsystem.dto;

import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.pwr.cinematicketsystem.entity.Show;

import java.util.List;

@Builder
@Getter
public class MovieResponse {
    private Integer id;
    private String title;
    private String description;
    private Integer durationMinutes;
    private String image_url;
    private List<ShowShortResponse> shows;
}
