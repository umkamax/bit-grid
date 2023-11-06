package grid.bit.mapper;

import grid.bit.dto.GridDTO;
import grid.bit.model.Grid;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

// TODO generic mapper
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GridMapper {
    Grid toEntity(GridDTO dto);
    GridDTO toDTO(Grid entity);
}
