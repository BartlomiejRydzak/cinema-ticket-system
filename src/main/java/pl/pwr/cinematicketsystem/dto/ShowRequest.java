package pl.pwr.cinematicketsystem.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
public class ShowRequest {
    private LocalDateTime date;
    private Integer room;
    private Integer movieId;
}
