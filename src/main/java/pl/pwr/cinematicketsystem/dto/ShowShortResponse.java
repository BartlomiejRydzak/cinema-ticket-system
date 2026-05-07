package pl.pwr.cinematicketsystem.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class ShowShortResponse {
    private LocalDateTime date;
    private Integer roomId;
}
