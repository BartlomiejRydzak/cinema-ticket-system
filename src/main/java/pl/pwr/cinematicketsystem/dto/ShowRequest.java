package pl.pwr.cinematicketsystem.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
public class ShowRequest {
    private LocalDateTime date;
    private Integer roomId;
    private Integer movieId;
}
