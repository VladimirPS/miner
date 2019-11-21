package orel.vpecherskii.minesweeper.support;

import orel.vpecherskii.minesweeper.config.Properties;
import orel.vpecherskii.minesweeper.model.CellsArray;

import static orel.vpecherskii.minesweeper.config.Properties.COL;
import static orel.vpecherskii.minesweeper.config.Properties.ROW;

public class Common {


    public static Boolean notOut(int x, int y) {
        return x >= 0 && x < ROW && y >= 0 && y < COL;
    }

    public static void fillCellsAroundZero(int x, int y, CellsArray cellsArrayUpper, CellsArray cellsArray) {
        for (int i = x - 1; i <= x + 1; i++)
            for (int j = y - 1; j <= y + 1; j++)
                if (notOut(i, j)) {
                    if (cellsArrayUpper.getCell(i, j).cellType == CellType.CLOSED && cellsArray.getCell(i, j).cellType == CellType.ZERO) {
                        cellsArrayUpper.getCell(i, j).setCellType(CellType.OPENED);
                        Properties.countClosed--;
                        fillCellsAroundZero(i, j, cellsArrayUpper, cellsArray);
                    }
                    if (cellsArray.getCell(i, j).cellType != CellType.ZERO && cellsArrayUpper.getCell(i, j).cellType != CellType.OPENED) {
                        cellsArrayUpper.getCell(i, j).setCellType(CellType.OPENED);
                        Properties.countClosed--;
                    }
                }
    }
}
