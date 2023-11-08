package grid.bit.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
public class ErrorInfo {
    @JsonProperty("code")
    private final int httpStatus;
    @JsonProperty("status")
    private final String reasonPhrase;
    private final String message;
    private final List<String> details;

    public ErrorInfo(HttpStatus status, String message) {
        this(status, message, List.of());

    }

    public ErrorInfo(HttpStatus status, String message, List<String> details) {
        this.httpStatus = status.value();
        this.reasonPhrase = status.getReasonPhrase();
        this.message = message;
        this.details = details;
    }

    public ErrorInfo(HttpStatus status, String message, String error) {
        this.httpStatus = status.value();
        this.reasonPhrase = status.getReasonPhrase();
        this.message = message;
        this.details = List.of(error);
    }

}
