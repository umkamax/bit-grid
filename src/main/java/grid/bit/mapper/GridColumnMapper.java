package grid.bit.mapper;

import grid.bit.dto.GridColumnDTO;
import grid.bit.model.GridColumn;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GridColumnMapper {
    GridColumn toEntity(GridColumnDTO dto);
    GridColumnDTO toDTO(GridColumn entity);
}
