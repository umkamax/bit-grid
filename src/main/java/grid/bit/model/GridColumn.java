package grid.bit.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "grid_column")
@NoArgsConstructor
public class GridColumn extends AbstractEntity<Long> {
    private int number;
    private String lcp;
    private Grid grid;
    private Set<GridCell> cells;

    public GridColumn(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grid_id")
    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "gridColumn", cascade = CascadeType.REMOVE, orphanRemoval = true)
    public Set<GridCell> getCells() {
        return cells;
    }

    public void setCells(Set<GridCell> cells) {
        this.cells = cells;
    }
}