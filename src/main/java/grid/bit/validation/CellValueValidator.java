package grid.bit.validation;

import grid.bit.model.GridCell;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Formatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Objects.isNull;

public class CellValueValidator implements ConstraintValidator<ValidCellValue, GridCell> {
    public static final String BIT_STRING_PATTERN = "^[0-1]+$";

    private Pattern pattern;

    public void initialize(ValidCellValue constraint) {
        pattern = Pattern.compile(BIT_STRING_PATTERN);
    }

    public boolean isValid(GridCell cell, ConstraintValidatorContext context) {
        if (isNull(cell)) {
            return false;
        }

        if(isNull(cell.getGridRow()) || isNull(cell.getGridColumn()) || isNull(cell.getValue())) {
            return false;
        }

        return checkCellValueFormat(cell);
    }

    // TODO validate bit string @Pattern
    private boolean checkCellValueFormat(GridCell gridCell) {
        int cellSize = gridCell.getGridColumn().getGrid().getCellSize();
        String cellValue = gridCell.getValue();
        Matcher m = pattern.matcher(cellValue);

        return gridCell.getValue().length() == cellSize && m.matches();
    }
}
