package pl.pwr.cinematicketsystem.dto;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class ShowRequest {

    private LocalDateTime date;
    private Integer roomId;
    private Integer movieId;
}
