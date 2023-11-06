package grid.bit.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GridColumnDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private final Long id;

    private final int number;
}
