package orel.vpecherskii.minesweeper.model;

import orel.vpecherskii.minesweeper.support.CellType;
import orel.vpecherskii.minesweeper.config.Properties;
import orel.vpecherskii.minesweeper.support.CellLevel;
import orel.vpecherskii.minesweeper.support.CellsArray;
import orel.vpecherskii.minesweeper.support.GameState;

import java.awt.event.MouseEvent;

public class MineSweeperModel {
    private CellsArray cellsArray;
    private CellsArray cellsArrayUpper;

    public MineSweeperModel() {
        reInitialize();
    }

    public void reInitialize(){
        this.cellsArray = new CellsArray(Properties.ROW, Properties.COL, CellType.ZERO);
        this.cellsArrayUpper = new CellsArray(Properties.ROW, Properties.COL, CellType.CLOSED);
        GameState.gameState=GameState.PLAYING;

    }

    public CellsArray getCellsArray() {
        return cellsArray;
    }

    public CellsArray getCellsArrayUpper() {
        return cellsArrayUpper;
    }

    public CellType getCellType(CellLevel level, MouseEvent e) {
        return this.getCells(level).getCell(e.getX() / 50, e.getY() / 50).cellType;
    }

    public CellsArray getCells(CellLevel level) {
        switch (level) {
            case BACK:
                return this.getCellsArray();
            case FRONT:
                return this.getCellsArrayUpper();
            default:
                throw new Error("This is impossible");
        }
    }

}
