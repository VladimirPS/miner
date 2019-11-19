package orel.vpecherskii.minesweeper.config;

import orel.vpecherskii.minesweeper.model.CellsArray;
import orel.vpecherskii.minesweeper.support.CellType;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Properties extends JFrame {

    public static int totalBombs;
    static int totalFlags;
    public static int ROW;
    public static int COL;
    public final int IMG_SIZE = 50;
    public final int MENUHIN = 40;
    public static int countClosed;

    public Properties() {
        ROW = 10;
        COL = 10;
        totalFlags = 20;
        totalBombs = 2;
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

    public static Boolean notOut(int x, int y) {
        return x >= 0 && x < ROW && y >= 0 && y < COL;
    }

    public static void fillCellsAroundZero(int x, int y, CellsArray cellsArrayUpper, CellsArray cellsArray) {
        switch (cellsArray.getCell(x, y).cellType) {
            case ZERO:
                for (int i = x - 1; i <= x + 1; i++)
                    for (int j = y - 1; j <= y + 1; j++)
                        if (Properties.notOut(i, j)) {
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
                break;

        }
    }

    public Image getImage1(String s) {
        String filename = "/" + s.toLowerCase() + ".png";
        URL url = getClass().getResource("/" + s.toLowerCase() + ".png");
        ImageIcon icon = new ImageIcon(url);
        return icon.getImage();
    }
}



