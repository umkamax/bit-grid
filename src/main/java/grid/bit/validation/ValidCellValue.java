package grid.bit.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CellValueValidator.class)
@Documented
public @interface ValidCellValue {
//    String message() default "Cell value must be a valid bit string of size: ${validatedValue.gridColumn.grid.cellSize} [Grid#getCellSize()]";
//    String message() default "${formatter.format('%d', validatedValue.gridColumn.grid.cellSize)}";
    String message() default "{validation.cellValue}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
