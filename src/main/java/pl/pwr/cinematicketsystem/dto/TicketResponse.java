package pl.pwr.cinematicketsystem.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TicketResponse {
    private Integer id;
    private Integer showId;
    private Integer seatId;
}
