package sample;

import java.util.Random;

public class Generator {
    Generator() {
        Cell[][] array = new Cell[10][10];
        for (int x = 0; x < array.length; x++) {
            for (int y = 0; y < array[x].length; y++) {
                array[x][y] = new Cell(x, y, Statement.BOMB);
            }
        }

        for (int i = 0; i < 10; i++) {
            final Random random = new Random();
            int x = random.nextInt(10);
            int y = random.nextInt(10);
            array[x][y] = new Cell(1,1 , Statement.BOMB);
            array[x-1][y-1].statement = Statement.ONE;
        }


        for (Cell[] anArr : array) {
            for (Cell anAnArr : anArr) {
                System.out.print(anAnArr.statement + " ");
            }
            System.out.println();
        }
    }

}
