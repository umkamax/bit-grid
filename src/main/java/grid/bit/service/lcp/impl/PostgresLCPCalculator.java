package grid.bit.service.lcp.impl;

import grid.bit.model.GridColumn;
import grid.bit.repository.GridCellRepository;
import grid.bit.service.lcp.LCPAlgorithm;
import grid.bit.service.lcp.LCPCalculator;
import org.springframework.stereotype.Component;

import static grid.bit.service.lcp.LCPAlgorithm.POSTGRES;

@Component
public class PostgresLCPCalculator implements LCPCalculator {
    private final GridCellRepository gridCellRepository;

    public PostgresLCPCalculator(GridCellRepository gridCellRepository) {
        this.gridCellRepository = gridCellRepository;
    }

    @Override
    public LCPAlgorithm getAlgorithm() {
        return POSTGRES;
    }

    @Override
    public String calculate(GridColumn column) {
        return gridCellRepository.getCommonPrefix(column.getId());
    }
}
