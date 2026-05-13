package pl.pwr.cinematicketsystem.dto;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ShowResponse {

    private LocalDateTime date;
    private Integer durationMinutes;
    private String movieTitle;
    private String description;
    private String imgUrl;
}
