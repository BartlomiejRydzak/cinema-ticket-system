package pl.pwr.cinematicketsystem.dto;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import pl.pwr.cinematicketsystem.entity.Room;

@Builder
@Getter
public class SeatResponse {
    private Integer rowNumber;
    private Integer seatNumber;
    private Integer roomId;
    private Boolean reserved;
}
