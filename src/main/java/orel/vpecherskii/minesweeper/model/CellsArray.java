package orel.vpecherskii.minesweeper.model;

import orel.vpecherskii.minesweeper.view.Cell;
import orel.vpecherskii.minesweeper.support.CellType;
import orel.vpecherskii.minesweeper.config.Properties;

public class CellsArray {

    private Cell[][] cellsArray;


    CellsArray(int ROW, int COL, CellType cellType, Properties properties) {
        cellsArray = new Cell[ROW][COL];

        for (int x = 0; x < ROW; x++)
            for (int y = 0; y < COL; y++)
                cellsArray[x][y] = new Cell(x, y, cellType, properties);
    }

    public Cell getCell(int x, int y) {
        if (Properties.notOut(x, y)) {
            return cellsArray[x][y];
        }
        return null;
    }

    public void set(int x, int y, CellType cellType) {
        if (Properties.notOut(x, y))
            cellsArray[x][y].setCellType(cellType);

    }

    public Cell[][] getCellsArray() {
        return cellsArray;
    }


}
