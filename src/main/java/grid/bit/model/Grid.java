package grid.bit.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "grid")
public class Grid extends AbstractEntity<Long> {
    private String name;
    private int cellSize;
    private List<GridColumn> columns = new ArrayList<>();
    private List<GridRow> rows = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "cell_size")
    public int getCellSize() {
        return cellSize;
    }

    public void setCellSize(int cellSize) {
        this.cellSize = cellSize;
    }

    @OrderBy("number") // ???
    @OneToMany(mappedBy = "grid", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<GridColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<GridColumn> columns) {
        this.columns = columns;
    }

    @OrderBy("number") // ???
    @OneToMany(mappedBy = "grid", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<GridRow> getRows() {
        return rows;
    }

    public void setRows(List<GridRow> rows) {
        this.rows = rows;
    }

    public void addColumn(GridColumn column) {
        columns.add(column);
        column.setGrid(this);
    }

    public void addRow(GridRow row) {
        rows.add(row);
        row.setGrid(this);
    }
}