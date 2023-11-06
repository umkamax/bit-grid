package grid.bit.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
public class ErrorInfo {
    private final HttpStatus status;
    private final String message;
    private final List<String> errors;

    public ErrorInfo(HttpStatus status, String message) {
        this(status, message, List.of());
    }

    public ErrorInfo(HttpStatus status, String message, List<String> errors) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public ErrorInfo(HttpStatus status, String message, String error) {
        super();
        this.status = status;
        this.message = message;
        this.errors = List.of(error);
    }

}
