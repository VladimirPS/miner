package orel.vpecherskii.minesweeper.controller;

import orel.vpecherskii.minesweeper.config.Properties;
import orel.vpecherskii.minesweeper.model.MineSweeperModel;
import orel.vpecherskii.minesweeper.support.CellLevel;
import orel.vpecherskii.minesweeper.support.CellType;
import orel.vpecherskii.minesweeper.support.Common;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;

public class ActionsController implements MouseListener, MouseMotionListener {

    JPanel panel;
    private MineSweeperModel mineSweeperModel;


    public ActionsController(JPanel panel, MineSweeperModel mineSweeperModel) {
        this.panel = panel;
        this.mineSweeperModel = mineSweeperModel;
    }

    public void restart() {
        mineSweeperModel.reInitialize();

    }

    private void generateBombs(int x1, int y1) {
        for (int q = 0; q < Properties.totalBombs; ) {
            int x = new Random().nextInt(Properties.ROW);
            int y = new Random().nextInt(Properties.COL);
            if (mineSweeperModel.getCells(CellLevel.BACK).getCellsArray()[x][y].cellType != CellType.BOMB && x != x1 && y != y1) {
                mineSweeperModel.getCells(CellLevel.BACK).set(x, y, CellType.BOMB);
                q++;
                for (int i = x - 1; i <= x + 1; i++)
                    for (int j = y - 1; j <= y + 1; j++)
                        if (Common.notOut(i, j)) {
                            if (mineSweeperModel.getCells(CellLevel.BACK).getCellsArray()[i][j].cellType != CellType.BOMB)
                                mineSweeperModel.getCells(CellLevel.BACK).set(i, j, mineSweeperModel.getCells(CellLevel.BACK).getCellsArray()[i][j].statementInc());
                        }

            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // if all cells was clicked ?
        if (Properties.countClosed == Properties.ROW * Properties.COL) {
            this.generateBombs(e.getX() / 50, e.getY() / 50);

            mineSweeperModel.getCells(CellLevel.FRONT).set(e.getX() / 50, e.getY() / 50, CellType.OPENED);

            if (mineSweeperModel.getCells(CellLevel.BACK).getCellsArray()[e.getX() / 50][e.getY() / 50].cellType == CellType.ZERO)
                Common.fillCellsAroundZero(e.getX() / 50, e.getY() / 50, mineSweeperModel.getCells(CellLevel.FRONT), mineSweeperModel.getCells(CellLevel.BACK));
            Properties.countClosed--;
            panel.repaint();
        }

        //Handle click on not opened cell
        if (mineSweeperModel.getCellType(CellLevel.FRONT, e) != CellType.OPENED) {
            switch (e.getButton()) {
                case (MouseEvent.BUTTON1):
                    switch (mineSweeperModel.getCellsArray().getCell(e.getX() / 50, e.getY() / 50).cellType) {
                        case BOMB:
                            if (mineSweeperModel.getCellType(CellLevel.FRONT, e) != CellType.FLAG) {
                                mineSweeperModel.getCellsArray().set(e.getX() / 50, e.getY() / 50, CellType.BOMBED);
                                mineSweeperModel.getCellsArrayUpper().set(e.getX() / 50, e.getY() / 50, CellType.OPENED);
                                Properties.countClosed--;
                                for (int x = 0; x < Properties.ROW; x++) {
                                    for (int y = 0; y < Properties.COL; y++) {
                                        if
                                        (mineSweeperModel.getCellsArrayUpper().getCell(x, y).cellType == CellType.FLAG &&
                                                mineSweeperModel.getCellsArray().getCell(x, y).cellType != CellType.BOMB) {
                                            mineSweeperModel.getCellsArrayUpper().getCell(x, y).setCellType(CellType.NOBOMB);
                                            Properties.countClosed--;
                                        } else if (mineSweeperModel.getCellsArrayUpper().getCell(x, y).cellType != CellType.FLAG) {
                                            mineSweeperModel.getCellsArrayUpper().getCell(x, y).setCellType(CellType.OPENED);
                                            Properties.countClosed--;
                                        }
                                    }
                                }
                                panel.repaint();
                            }
                            break;
                        case ZERO:
                            if (mineSweeperModel.getCellType(CellLevel.FRONT, e) != CellType.FLAG) {
                                Common.fillCellsAroundZero(e.getX() / 50, e.getY() / 50, mineSweeperModel.getCellsArrayUpper(), mineSweeperModel.getCellsArray());
                                panel.repaint();
                            }
                            break;
                        default:
                            if (mineSweeperModel.getCellType(CellLevel.FRONT, e) != CellType.FLAG) {
                                mineSweeperModel.getCellsArrayUpper().set(e.getX() / 50, e.getY() / 50, CellType.OPENED);
                                Properties.countClosed--;
                                panel.repaint();
                            }
                            break;
                    }
                    break;
                case (MouseEvent.BUTTON3):
                    switch (mineSweeperModel.getCellType(CellLevel.FRONT, e)) {
                        case FLAG:
                            mineSweeperModel.getCellsArrayUpper().set(e.getX() / 50, e.getY() / 50, CellType.CLOSED);
                            panel.repaint();
                            break;
                        case CLOSED:
                            mineSweeperModel.getCellsArrayUpper().set(e.getX() / 50, e.getY() / 50, CellType.FLAG);
                            panel.repaint();
                            break;
                        default:
                            break;
                    }
                    break;
            }

            if (Properties.countClosed == Properties.totalBombs) {
                mineSweeperModel.getCellsArray().set(5, 5, CellType.WIN);
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
