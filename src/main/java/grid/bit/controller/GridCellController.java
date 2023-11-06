package grid.bit.controller;

import grid.bit.dto.ValueDTO;
import grid.bit.model.GridCell;
import grid.bit.service.GridCellService;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RequestMapping("grid/cell")
@RestController
public class GridCellController {
    private final GridCellService gridCellService;

    public GridCellController(GridCellService gridCellService) {
        this.gridCellService = gridCellService;
    }

    @ResponseStatus(NO_CONTENT)
    @PostMapping("{columnId}/{rowId}")
    public void set(
            @PathVariable("columnId") Long columnId,
            @PathVariable("rowId") Long rowId,
            @RequestBody ValueDTO valueDTO) {
        GridCell gridCell = gridCellService.createGridCell(rowId, columnId, valueDTO.getValue());

        gridCellService.save(gridCell);
    }
}