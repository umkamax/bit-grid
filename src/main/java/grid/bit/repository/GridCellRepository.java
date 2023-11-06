package grid.bit.repository;

import grid.bit.model.GridCell;
import grid.bit.model.embeddable.GridCellId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GridCellRepository extends JpaRepository<GridCell, GridCellId> {
}
