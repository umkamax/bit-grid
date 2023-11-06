package grid.bit.service;

import grid.bit.model.GridCell;
import grid.bit.model.GridColumn;
import grid.bit.model.GridRow;
import grid.bit.repository.GridCellRepository;
import grid.bit.validation.ValidCellValue;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@Transactional
@Validated
public class GridCellService {
    private final GridColumnService gridColumnService;
    private final GridRowService gridRowService;
    private final GridCellRepository gridCellRepository;

    public GridCellService(
            GridColumnService gridColumnService,
            GridRowService gridRowService,
            GridCellRepository gridCellRepository
    ) {
        this.gridColumnService = gridColumnService;
        this.gridRowService = gridRowService;
        this.gridCellRepository = gridCellRepository;
    }

    @ValidCellValue
    public GridCell createGridCell(Long rowId, Long columnId, String cellValue) {
        GridRow row = gridRowService.getById(rowId);
        GridColumn column = gridColumnService.getById(columnId);

        return new GridCell(row, column, cellValue);
    }

    public void save(GridCell gridCell) {
        gridCellRepository.save(gridCell);
    }
}