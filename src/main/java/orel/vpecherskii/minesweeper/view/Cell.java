package orel.vpecherskii.minesweeper.view;

import orel.vpecherskii.minesweeper.config.Properties;
import orel.vpecherskii.minesweeper.support.CellType;

import javax.swing.*;
import java.awt.*;

public class Cell extends JFrame {
    public CellType cellType;
    int x;
    int y;
    Properties properties;


    public CellType getCellType() {
        return cellType;
    }


//    public void setImage(Image image) {
//        image = getImage(statement.toString());
//    }

    public Image getImage() {
        return image;
    }

    public Image image;

    public Cell(int x, int y, CellType cellType, Properties properties) {
        this.x = x;
        this.y = y;
        this.cellType = cellType;
        this.properties = properties;
        image = properties.getImage1(cellType.toString());


    }

    public void setCellType(CellType cellType) {
        this.cellType = cellType;
        image = properties.getImage1(cellType.toString());

    }

    public CellType statementInc() {
        this.cellType = cellType.values()[cellType.ordinal() + 1];
        return this.cellType;
    }


}
