package orel.vpecherskii.minesweeper.controller;

import com.sun.prism.Graphics;
import orel.vpecherskii.minesweeper.config.Properties;
import orel.vpecherskii.minesweeper.model.FieldModel;
import orel.vpecherskii.minesweeper.support.CellLevel;
import orel.vpecherskii.minesweeper.support.CellType;
import orel.vpecherskii.minesweeper.support.GameState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.ImageObserver;
import java.util.Random;

public class ActionsController implements MouseListener, MouseMotionListener {

    JPanel panel;
    private FieldModel fieldModel;
    private Properties properties;


    public ActionsController(JPanel panel, FieldModel fieldModel, Properties properties) {
        this.panel = panel;
        this.fieldModel = fieldModel;
        this.properties = properties;

    }

    private void generateBombs(int x1, int y1) {
        GameState.setGameState(GameState.PLAYING);
        for (int q = 0; q < Properties.totalBombs; ) {
            int x = new Random().nextInt(Properties.ROW);
            int y = new Random().nextInt(Properties.COL);
            if (fieldModel.getCells(CellLevel.BACK).getCellsArray()[x][y].cellType != CellType.BOMB && x != x1 && y != y1) {
                fieldModel.getCells(CellLevel.BACK).set(x, y, CellType.BOMB);
                q++;
                for (int i = x - 1; i <= x + 1; i++)
                    for (int j = y - 1; j <= y + 1; j++)
                        if (Properties.notOut(i, j)) {
                            if (fieldModel.getCells(CellLevel.BACK).getCellsArray()[i][j].cellType != CellType.BOMB)
                                fieldModel.getCells(CellLevel.BACK).set(i, j, fieldModel.getCells(CellLevel.BACK).getCellsArray()[i][j].statementInc());
                        }

            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // if all cells was clicked ?
        if (Properties.countClosed == Properties.ROW * Properties.COL) {
            this.generateBombs(e.getX() / 50, e.getY() / 50);

            fieldModel.getCells(CellLevel.FRONT).set(e.getX() / 50, e.getY() / 50, CellType.OPENED);

            if (fieldModel.getCells(CellLevel.BACK).getCellsArray()[e.getX() / 50][e.getY() / 50].cellType == CellType.ZERO)
                Properties.fillCellsAroundZero(e.getX() / 50, e.getY() / 50, fieldModel.getCells(CellLevel.FRONT), fieldModel.getCells(CellLevel.BACK));
            Properties.countClosed--;
            panel.repaint();
        }

        //Handle click on not opened cell
        if (fieldModel.getCellType(CellLevel.FRONT, e) != CellType.OPENED) {
            switch (e.getButton()) {
                case (MouseEvent.BUTTON1):
                    switch (fieldModel.getCellsArray().getCell(e.getX() / 50, e.getY() / 50).cellType) {
                        case BOMB:
                            if (fieldModel.getCellType(CellLevel.FRONT, e) != CellType.FLAG) {
                                fieldModel.getCellsArray().set(e.getX() / 50, e.getY() / 50, CellType.BOMBED);
                                fieldModel.getCellsArrayUpper().set(e.getX() / 50, e.getY() / 50, CellType.OPENED);
                                Properties.countClosed--;
                                GameState.setGameState(GameState.LOSE);
                                for (int x = 0; x < Properties.ROW; x++) {
                                    for (int y = 0; y < Properties.COL; y++) {
                                        if
                                        (fieldModel.getCellsArrayUpper().getCell(x, y).cellType == CellType.FLAG &&
                                                fieldModel.getCellsArray().getCell(x, y).cellType != CellType.BOMB) {
                                            fieldModel.getCellsArrayUpper().getCell(x, y).setCellType(CellType.NOBOMB);
                                            Properties.countClosed--;
                                        } else if (fieldModel.getCellsArrayUpper().getCell(x, y).cellType != CellType.FLAG) {
                                            fieldModel.getCellsArrayUpper().getCell(x, y).setCellType(CellType.OPENED);
                                            Properties.countClosed--;
                                        }
                                    }
                                }
                                panel.repaint();
                            }
                            break;
                        case ZERO:
                            if (fieldModel.getCellType(CellLevel.FRONT, e) != CellType.FLAG) {
                                properties.fillCellsAroundZero(e.getX() / 50, e.getY() / 50, fieldModel.getCellsArrayUpper(), fieldModel.getCellsArray());
                                panel.repaint();
                            }
                            break;
                        default:
                            if (fieldModel.getCellType(CellLevel.FRONT, e) != CellType.FLAG) {
                                fieldModel.getCellsArrayUpper().set(e.getX() / 50, e.getY() / 50, CellType.OPENED);
                                Properties.countClosed--;
                                panel.repaint();
                            }
                            break;
                    }
                    break;
                case (MouseEvent.BUTTON3):
                    switch (fieldModel.getCellType(CellLevel.FRONT, e)) {
                        case FLAG:
                            fieldModel.getCellsArrayUpper().set(e.getX() / 50, e.getY() / 50, CellType.CLOSED);
                            panel.repaint();
                            break;
                        case CLOSED:
                            fieldModel.getCellsArrayUpper().set(e.getX() / 50, e.getY() / 50, CellType.FLAG);
                            panel.repaint();
                            break;
                        default:
                            break;
                    }
                    break;
            }

            if (Properties.countClosed == Properties.totalBombs) {
                GameState.setGameState(GameState.WIN);
                fieldModel.getCellsArray().set(5, 5, CellType.WIN);
                panel.repaint();
            }
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {


    }
}
