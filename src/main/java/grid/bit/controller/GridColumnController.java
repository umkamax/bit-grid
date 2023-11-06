package grid.bit.controller;

import grid.bit.dto.GridColumnDTO;
import grid.bit.dto.ValueDTO;
import grid.bit.mapper.GridColumnMapper;
import grid.bit.model.GridColumn;
import grid.bit.service.GridColumnService;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RequestMapping("grid/column")
@RestController
public class GridColumnController {
    private final GridColumnService gridColumnService;
    private final GridColumnMapper mapper;

    public GridColumnController(GridColumnService gridColumnService, GridColumnMapper mapper) {
        this.gridColumnService = gridColumnService;
        this.mapper = mapper;
    }

    @ResponseStatus(CREATED)
    @PostMapping
    public GridColumnDTO insert(@RequestParam("afterColumnId") Long afterColumnId) {
        GridColumn gridColumn = gridColumnService.insert(afterColumnId);

        return mapper.toDTO(gridColumn);
    }

    @ResponseStatus(NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        gridColumnService.delete(id);
    }

    @ResponseStatus(OK)
    @GetMapping("{id}/common-prefix")
    public ValueDTO getCommonPrefix(@PathVariable("id") Long id) {
        String value = gridColumnService.getCommonPrefix(id);

        return ValueDTO.builder()
                .value(value)
                .build();
    }
}