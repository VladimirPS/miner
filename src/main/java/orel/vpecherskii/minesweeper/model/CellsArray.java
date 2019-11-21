package orel.vpecherskii.minesweeper.model;

import orel.vpecherskii.minesweeper.support.CellType;
import orel.vpecherskii.minesweeper.view.Cell;

import static orel.vpecherskii.minesweeper.support.Common.notOut;

public class CellsArray {

    private Cell[][] cellsArray;


    CellsArray(int ROW, int COL, CellType cellType) {
        cellsArray = new Cell[ROW][COL];

        for (int x = 0; x < ROW; x++)
            for (int y = 0; y < COL; y++)
                cellsArray[x][y] = new Cell(x, y, cellType);
    }

    public Cell getCell(int x, int y) {
        if (notOut(x, y)) {
            return cellsArray[x][y];
        }
        return null;
    }

    public void set(int x, int y, CellType cellType) {
        if (notOut(x, y))
            cellsArray[x][y].setCellType(cellType);

    }

    public Cell[][] getCellsArray() {
        return cellsArray;
    }


}
