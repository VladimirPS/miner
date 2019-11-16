package sample;

import sun.misc.IOUtils;

import javax.swing.*;
import java.awt.*;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;

public class Properties extends JFrame {

    static int totalBombs;
    static int totalFlags;
    public static int ROW;
    public static int COL;
    public final int IMG_SIZE = 50;
    public final int MENUHIN = 40;
    static int countClosed;

    public Properties() {
        ROW = 13;
        COL = 15;
        totalFlags = 20;
        totalBombs = 1;
        this.countClosed = ROW * COL;
    }

    void setTotalBombsAndFlags(int x) {
        totalBombs = x;
        totalFlags = totalBombs;
    }

    int getTotalBombs() {
        return totalBombs;
    }

    public static int getTotalFlags() {
        return totalFlags;
    }

    static Boolean notOut(int x, int y) {
        return x >= 0 && x < ROW && y >= 0 && y < COL;
    }

    static void aroundZero(int x, int y, CellsArray cellsArrayUpper, CellsArray cellsArray) {
        switch (cellsArray.getCell(x, y).statement) {
            case ZERO:
                for (int i = x - 1; i <= x + 1; i++)
                    for (int j = y - 1; j <= y + 1; j++)
                        if (Properties.notOut(i, j)) {
                            if (cellsArrayUpper.getCell(i, j).statement == Statement.CLOSED && cellsArray.getCell(i, j).statement == Statement.ZERO) {
                                cellsArrayUpper.getCell(i, j).setStatement(Statement.OPENED);
                                Properties.countClosed--;
                                aroundZero(i, j, cellsArrayUpper, cellsArray);
                            }
                            if (cellsArray.getCell(i, j).statement != Statement.ZERO && cellsArrayUpper.getCell(i, j).statement != Statement.OPENED) {
                                cellsArrayUpper.getCell(i, j).setStatement(Statement.OPENED);
                                Properties.countClosed--;
                            }
                        }
                break;

        }
    }
    public Image getImage1(String s) {
        String filename = "/"+s.toLowerCase()+".png";
        URL url = getClass().getResource("/" + s.toLowerCase() +".png");
//        System.out.println(s.toLowerCase());
//        System.out.println(url);
//        System.out.println(filename);
        ImageIcon icon = new ImageIcon(url);
        return icon.getImage();
    }
}



