package pl.pwr.cinematicketsystem.exception;

import java.time.Instant;
import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException {

    private final String errorCode;
    private final Instant timestamp;

    public ResourceNotFoundException(String message) {
        super(message);
        this.errorCode = "ENTITY_NOT_FOUND";
        this.timestamp = Instant.now();
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = "ENTITY_NOT_FOUND";
        this.timestamp = Instant.now();
    }
}
