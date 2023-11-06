package grid.bit.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serializable;
import java.text.MessageFormat;

@ResponseStatus(HttpStatus.NOT_FOUND)
@Getter
public class EntityNotFoundException extends RuntimeException {
    private final Serializable id;
    private final String entityName;

    public EntityNotFoundException(final Serializable id, final String entityName) {
        this.id = id;
        this.entityName = entityName;
    }

    @Override
    public String toString() {
        return MessageFormat.format(
                "EntityNotFoundException[entity={0}, id={1}]",
                this.entityName,
                this.id.toString()
        );
    }
}
