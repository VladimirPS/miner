package orel.vpecherskii.minesweeper.model;

import orel.vpecherskii.minesweeper.support.CellType;
import orel.vpecherskii.minesweeper.config.Properties;
import orel.vpecherskii.minesweeper.support.CellLevel;

import java.awt.event.MouseEvent;

public class FieldModel {
    private CellsArray cellsArray;
    private CellsArray cellsArrayUpper;

    public FieldModel(Properties properties) {
        this.cellsArray = new CellsArray(properties.ROW, properties.COL, CellType.ZERO, properties);
        this.cellsArrayUpper = new CellsArray(properties.ROW, properties.COL, CellType.CLOSED, properties);
    }

    public CellsArray getCellsArray() {
        return cellsArray;
    }

    public CellsArray getCellsArrayUpper() {
        return cellsArrayUpper;
    }

    public CellType getCellType(CellLevel level, MouseEvent e) {
        return this.getCells(level).getCell(e.getX() / 50, e.getY() / 50).cellType;
    }

    public CellsArray getCells(CellLevel level) {
        switch (level) {
            case BACK:
                return this.getCellsArray();
            case FRONT:
                return this.getCellsArrayUpper();
            default:
                throw new Error("This is impossible");
        }
    }

}
