package grid.bit.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GridDTO {
    @JsonProperty(access = Access.READ_ONLY)
    private final Long id;

    @NotBlank
    @Size(max = 200)
    private final String name;

    @Positive
    @Max(100000)
    private final int cellSize;
}
