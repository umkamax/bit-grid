package grid.bit.service;

import grid.bit.exception.EntityNotFoundException;
import grid.bit.model.Grid;
import grid.bit.model.GridColumn;
import grid.bit.model.GridRow;
import grid.bit.model.projection.GridSummary;
import grid.bit.repository.GridRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GridService {
    private final GridRepository gridRepository;

    public GridService(GridRepository gridRepository) {
        this.gridRepository = gridRepository;
    }

    @Transactional(readOnly = true)
    public List<GridSummary> getAll() {
        return gridRepository.findBy(GridSummary.class);
    }

    public Grid create(Grid grid) {
        grid.addColumn(new GridColumn(1));
        grid.addRow(new GridRow(1));

        return gridRepository.save(grid);
    }

    public void update(Long id, Grid grid) {
        Grid exists = gridRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, Grid.class.getSimpleName()));

        exists.setName(grid.getName());
    }

    public void delete(Long id) {
        gridRepository.deleteById(id);
    }
}