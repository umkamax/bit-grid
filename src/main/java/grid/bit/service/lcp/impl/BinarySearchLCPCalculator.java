package grid.bit.service.lcp.impl;

import grid.bit.model.GridColumn;
import grid.bit.service.lcp.AbstractLCPCalculator;
import grid.bit.service.lcp.LCPAlgorithm;
import org.apache.commons.lang3.NotImplementedException;

import static grid.bit.service.lcp.LCPAlgorithm.BINARY_SEARCH;

public class BinarySearchLCPCalculator extends AbstractLCPCalculator {
    @Override
    public LCPAlgorithm getAlgorithm() {
        return BINARY_SEARCH;
    }

    @Override
    public String calculate(GridColumn column) {
        var values = this.getValues(column);

        throw new NotImplementedException();
    }

}
