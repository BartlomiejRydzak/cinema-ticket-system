package pl.pwr.cinematicketsystem.dto;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.pwr.cinematicketsystem.entity.Movie;

import java.time.LocalDateTime;

@Builder
@Getter
public class ShowResponse {
    private Integer id;
    private LocalDateTime date;
    private Integer room;
    private Integer movieId;
    private String movieTitle;
}
