package grid.bit.service.lcp;

import grid.bit.model.GridCell;
import grid.bit.model.GridColumn;

import java.util.List;

public abstract class AbstractLCPCalculator implements LCPCalculator {
    public abstract String calculate(GridColumn column);

    protected List<String> getValues(GridColumn column) {
        return column.getCells().stream()
                .map(GridCell::getValue)
                .toList();
    }
}
