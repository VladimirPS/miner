package sample;

import java.util.Random;

public class BombGenerator {
    BombGenerator(CellsArray cellsArray, int x1, int y1, CellsArray cellsArrayUpper) {
        for (int q = 0; q < Properties.totalBombs;) {
            int x = new Random().nextInt(Properties.ROW);
            int y = new Random().nextInt(Properties.COL);
            if (cellsArray.getCellsArray()[x][y].statement != Statement.BOMB&&x!=x1&&y!=y1) {
                cellsArray.set(x, y, Statement.BOMB);
                q++;
                for (int i = x - 1; i <= x + 1; i++)
                    for (int j = y - 1; j <= y + 1; j++)
                        if (Properties.notOut(i, j))
                            if (cellsArray.getCellsArray()[i][j].statement != Statement.BOMB)
                                cellsArray.set(i, j, cellsArray.getCellsArray()[i][j].statementInc());



            }
        }
    }
}


