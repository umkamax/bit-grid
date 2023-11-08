package grid.bit.service.lcp;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Component;

@Component
public class LCPCalculatorFactory {
    private final LCPCalculator postgresLCPCalculator;

    public LCPCalculatorFactory(LCPCalculator postgresLCPCalculator) {
        this.postgresLCPCalculator = postgresLCPCalculator;
    }

    public LCPCalculator getLCPCalculator(LCPAlgorithm algorithm) {
        switch (algorithm) {
            case POSTGRES:
                return postgresLCPCalculator;
            default:
                throw new NotImplementedException();
        }
    }
}