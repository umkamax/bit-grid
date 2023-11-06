package grid.bit.model.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class GridCellId implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long gridRowId;
    private Long gridColumnId;

    @Column(name = "grid_row_id")
    public Long getGridRowId() {
        return gridRowId;
    }

    @Column(name = "grid_column_id")
    public Long getGridColumnId() {
        return gridColumnId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        GridCellId other = (GridCellId) obj;
        return Objects.equals(getGridRowId(), other.getGridRowId()) && Objects.equals(getGridColumnId(), other.getGridColumnId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(gridRowId, gridColumnId);
    }
}
