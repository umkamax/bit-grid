package grid.bit.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import grid.bit.model.embeddable.GridCellId;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

import java.util.Objects;

@Entity
@NoArgsConstructor
public class GridCell implements Persistable<GridCellId> {
    private GridCellId id;
    @JsonIgnore
    private GridRow gridRow;
    @JsonIgnore
    private GridColumn gridColumn;

    private String value;

    public GridCell(GridRow gridRow, GridColumn gridColumn) {
        this(gridRow, gridColumn, null);
    }

    public GridCell(GridRow gridRow, GridColumn gridColumn, String value) {
        this.id = new GridCellId(gridRow.getId(), gridColumn.getId());
        this.gridRow = gridRow;
        this.gridColumn = gridColumn;
        this.value = value;
    }

    @EmbeddedId
    public GridCellId getId() {
        return id;
    }

    public void setId(GridCellId id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("gridRowId")
    @JoinColumn(name = "grid_row_id")
    public GridRow getGridRow() {
        return gridRow;
    }

    public void setGridRow(GridRow gridRow) {
        this.gridRow = gridRow;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("gridColumnId")
    @JoinColumn(name = "grid_column_id")
    public GridColumn getGridColumn() {
        return gridColumn;
    }

    public void setGridColumn(GridColumn gridColumn) {
        this.gridColumn = gridColumn;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    @Transient
    public boolean isNew() {
        return Objects.isNull(getId());
    }
}
