package grid.bit.repository;

import grid.bit.model.GridCell;
import grid.bit.model.embeddable.GridCellId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GridCellRepository extends JpaRepository<GridCell, GridCellId> {
    @Query(
            value = "SELECT lcp(value) FROM grid_cell WHERE grid_column_id = :columnId",
            nativeQuery = true
    )
    String getCommonPrefix(@Param("columnId") Long columnId);
}
