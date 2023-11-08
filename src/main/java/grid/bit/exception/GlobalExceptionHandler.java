package grid.bit.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestControllerAdvice
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class GlobalExceptionHandler {

    private final MessageSourceAccessor messageSourceAccessor;

    public GlobalExceptionHandler(MessageSourceAccessor messageSourceAccessor) {
        this.messageSourceAccessor = messageSourceAccessor;
    }

    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    ErrorInfo handleRuntimeException(RuntimeException ex) {
        return new ErrorInfo(INTERNAL_SERVER_ERROR, ex.getLocalizedMessage());
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    ErrorInfo handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(error ->
                        "%s: %s".formatted(error.getField(), error.getDefaultMessage())
                )
                .collect(Collectors.toList());

        return new ErrorInfo(BAD_REQUEST, ex.getLocalizedMessage(), errors);
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    ErrorInfo handleConstraintViolationException(ConstraintViolationException ex) {
        List<String> errors = ex.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());

        return new ErrorInfo(BAD_REQUEST, ex.getLocalizedMessage(), errors);
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    ErrorInfo handleEntityNotFoundException(EntityNotFoundException ex) {
        log.debug(ex.toString());

        String message = MessageFormat.format(
                messageSourceAccessor.getMessage("exception.entityNotFound"),
                ex.getEntityName(),
                ex.getId().toString()
        );

        return new ErrorInfo(NOT_FOUND, message);
    }


}
