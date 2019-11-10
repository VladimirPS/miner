package orel.vpecherskii.minesweeper.view;

import orel.vpecherskii.minesweeper.support.CellType;
import orel.vpecherskii.minesweeper.support.ImageLoader;

import javax.swing.*;
import java.awt.*;

public class Cell extends JFrame {
    public CellType cellType;
    private int x;
    private int y;
    private ImageLoader imageLoader = new ImageLoader();
    private Image image;

    public Image getImage() {
        return image;
    }

    public Cell(int x, int y, CellType cellType) {
        this.x = x;
        this.y = y;
        this.cellType = cellType;
        image = imageLoader.loadImage(cellType.toString());


    }

    public void setCellType(CellType cellType) {
        this.cellType = cellType;
        image = imageLoader.loadImage(cellType.toString());

    }

    public CellType statementInc() {
        this.cellType = CellType.values()[cellType.ordinal() + 1];
        return this.cellType;
    }


}
