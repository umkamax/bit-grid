package grid.bit.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GridNameDTO {
    private final Long id;
    private final String name;
}
