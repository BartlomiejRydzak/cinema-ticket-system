package pl.pwr.cinematicketsystem.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ReservationResponse {
    private Integer id;
    private String code;
}
