package grid.bit.repository;

import grid.bit.model.Grid;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GridRepository extends JpaRepository<Grid, Long> {
    <T> List<T> findBy(Class<T> projection);
}