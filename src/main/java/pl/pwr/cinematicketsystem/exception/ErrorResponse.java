package pl.pwr.cinematicketsystem.exception;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse {

    private String errorCode;
    private String message;
    private Instant timestamp;
}
