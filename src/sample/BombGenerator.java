package sample;

import java.util.Random;

public class BombGenerator {
    BombGenerator(CellsArray cellsArray) {
        for (int q = 0; q<Properties.totalBombs;q++) {
            int x = new Random().nextInt(Properties.ROW);
            int y = new Random().nextInt(Properties.COL);
            if (cellsArray.getCell(x,y).statement!=Statement.BOMB)
            cellsArray.set(x, y, Statement.BOMB);
            for (int i = x-1;i<=x+1;i++)
                for (int j = y-1;j<=y+1;j++)
                    if (Properties.notOut(i,j))
                        if (cellsArray.getCellsArray()[i][j].statement!=Statement.BOMB)
                            cellsArray.set(i,j, cellsArray.getCellsArray()[i][j].statementInc());



        }
    }
}
