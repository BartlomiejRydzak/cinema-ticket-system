package pl.pwr.cinematicketsystem.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class ReservationRequest {
    private Integer showId;
    private List<Integer> seatIds;
}
