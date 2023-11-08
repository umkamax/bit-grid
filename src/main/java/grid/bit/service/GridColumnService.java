package grid.bit.service;

import grid.bit.exception.EntityNotFoundException;
import grid.bit.model.Grid;
import grid.bit.model.GridCell;
import grid.bit.model.GridColumn;
import grid.bit.model.GridRow;
import grid.bit.repository.GridColumnRepository;
import grid.bit.service.lcp.LCPCalculatorFactory;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static grid.bit.service.lcp.LCPAlgorithm.POSTGRES;

@Service
@Transactional
public class GridColumnService {
    // i'm forgot naming (e. g. multiplicator for multiply operation) :)))
    private static final int NUMBER_STEP = 1;

    private final GridColumnRepository gridColumnRepository;
    private final LCPCalculatorFactory lcpCalculatorFactory;

    public GridColumnService(
            GridColumnRepository gridColumnRepository,
            LCPCalculatorFactory lcpCalculatorFactory
    ) {
        this.gridColumnRepository = gridColumnRepository;
        this.lcpCalculatorFactory = lcpCalculatorFactory;
    }

    public GridColumn insert(Long afterColumnId) {
        GridColumn after = gridColumnRepository.findById(afterColumnId)
                .orElseThrow(() -> getEntityNotFoundException(afterColumnId));

        List<GridColumn> rearranged = insertAndRearrangeGridColumnsAfter(after);
        List<GridColumn> saved = gridColumnRepository.saveAllAndFlush(rearranged);

        return saved.stream()
                .filter(column -> Objects.equals(column.getNumber(), after.getNumber() + NUMBER_STEP))
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }

    protected List<GridColumn> insertAndRearrangeGridColumnsAfter(GridColumn after) {
        List<GridColumn> columns = after.getGrid().getColumns();

        GridColumn created = createGridColumn(after);
        int targetColumnIndex = columns.indexOf(after) + 1;
        columns.add(targetColumnIndex, created);

        /*
         * implement head/tail as alternative
         * https://stackoverflow.com/questions/19803058/java-8-stream-getting-head-and-tail
         *
         * */
        for(int i = targetColumnIndex; i < columns.size(); i++) {
            GridColumn current = columns.get(i);
            //TODO handle int overflow and throw IllegalStateException()
            current.setNumber(Math.addExact(current.getNumber(), NUMBER_STEP));
        }

        return columns;
    }

    private GridColumn createGridColumn(GridColumn afterColumn) {
        GridColumn column = new GridColumn();

        Grid grid = afterColumn.getGrid();
        column.setGrid(grid);

        int number = afterColumn.getNumber();
        column.setNumber(number);

        Set<GridCell> cells = createGridCellsInColumn(column);
        column.setCells(cells);

        return column;
    }

    private Set<GridCell> createGridCellsInColumn(GridColumn column) {
        List<GridRow> rows = column.getGrid().getRows();

        return rows.stream()
                .map(row -> createGridCell(row, column))
                .collect(Collectors.toSet());
    }

    private GridCell createGridCell(GridRow row, GridColumn column) {
        return new GridCell(row, column);
    }

    public void delete(Long id) {
        //TODO handle EmptyResultDataAccessException
        gridColumnRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public String getCommonPrefix(Long id) {
        var calculator = lcpCalculatorFactory.getLCPCalculator(POSTGRES);

        var column = gridColumnRepository.findById(id)
                .orElseThrow(() -> getEntityNotFoundException(id));

        // TODO move it into GridCellService for calculate upon saving (trigger/events)
        return calculator.calculate(column);
    }

    @Transactional(readOnly = true)
    public GridColumn getById(Long id) {
        return gridColumnRepository.findById(id)
                .orElseThrow(() -> getEntityNotFoundException(id));
    }

    private EntityNotFoundException getEntityNotFoundException(Long id) {
        return new EntityNotFoundException(id, GridColumn.class.getSimpleName());
    }
}