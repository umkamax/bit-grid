package grid.bit.service;

import grid.bit.exception.EntityNotFoundException;
import grid.bit.model.GridRow;
import grid.bit.repository.GridRowRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GridRowService {
    private final GridRowRepository gridRowRepository;

    public GridRowService(GridRowRepository gridRowRepository) {
        this.gridRowRepository = gridRowRepository;
    }

    @Transactional(readOnly = true)
    public GridRow getById(Long id) {
        return gridRowRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, GridRow.class.getSimpleName()));
    }
}