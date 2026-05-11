package pl.pwr.cinematicketsystem.dto;

import lombok.Builder;
import lombok.Getter;
import pl.pwr.cinematicketsystem.entity.TicketState;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
public class TicketValidatorResponse {
    private String ticketCode;
    private TicketState ticketState;
    private Integer numberOfReservedSeats;
    private LocalDateTime showDate;
    private List<SeatResponse> seats;
}
