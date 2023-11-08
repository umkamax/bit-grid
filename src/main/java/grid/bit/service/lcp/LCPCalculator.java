package grid.bit.service.lcp;

import grid.bit.model.GridColumn;

public interface LCPCalculator {
    LCPAlgorithm getAlgorithm(); // probably getType()

    String calculate(GridColumn column);
}
