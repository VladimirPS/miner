package sample;

import java.util.Random;

public class Generator {
    CellsArray cellsArray;
    CellsArray cellsArrayUpper;

    Generator(Properties properties) {
        this.cellsArray = new CellsArray(properties.ROW, properties.COL, Statement.ZERO, properties);
        this.cellsArrayUpper = new CellsArray(properties.ROW, properties.COL, Statement.CLOSED, properties);
//        for (Cell[] cells : cellsArray.getCellsArray()) {
//            System.out.println("");
//            for (Cell cell1 : cells) {
//                System.out.print(cell1.statement + "\t");
//            }
//        }

    }


}
