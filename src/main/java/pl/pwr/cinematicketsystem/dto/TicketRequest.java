package pl.pwr.cinematicketsystem.dto;

import lombok.Getter;

@Getter
public class TicketRequest {
    private Integer showId;
    private Integer seatId;
}
