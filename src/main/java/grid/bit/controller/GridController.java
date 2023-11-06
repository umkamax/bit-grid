package grid.bit.controller;

import grid.bit.dto.GridDTO;
import grid.bit.mapper.GridMapper;
import grid.bit.model.Grid;
import grid.bit.model.projection.GridSummary;
import grid.bit.service.GridService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RequestMapping("grid")
@RestController
public class GridController {
    private final GridService gridService;
    private final GridMapper mapper;

    public GridController(GridService gridService, GridMapper mapper) {
        this.gridService = gridService;
        this.mapper = mapper;
    }

    @GetMapping
    public List<GridSummary> getAll() {
        return gridService.getAll();
    }

    @ResponseStatus(CREATED)
    @PostMapping
    public GridDTO create(@Valid @RequestBody GridDTO gridDTO) {
        Grid grid = mapper.toEntity(gridDTO);
        Grid created = gridService.create(grid);

        return mapper.toDTO(created);
    }

    @ResponseStatus(NO_CONTENT)
//    @PatchMapping("/{id}")
    @PutMapping("/{id}")
    public void updateName(
            @PathVariable("id") Long id,
            @RequestBody GridDTO gridDTO
    ) {
        Grid update = mapper.toEntity(gridDTO);

        gridService.update(id, update);
    }

    @ResponseStatus(NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        gridService.delete(id);
    }
}