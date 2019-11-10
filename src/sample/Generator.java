package sample;

import java.util.Random;

public class Generator {

    Generator() {
            Properties properties = new Properties();
            CellsArray cellsArray = new CellsArray(properties.ROW, properties.COL);
            System.out.println(cellsArray.getCellsArray()[1][1].statement);
            cellsArray.getCellsArray()[1][1].setStatement(Statement.BOMB);
            System.out.println(cellsArray.getCellsArray()[1][1].statement);


    }
}
