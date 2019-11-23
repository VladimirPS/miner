package orel.vpecherskii.minesweeper.controller;

import orel.vpecherskii.minesweeper.MinesweeperApp;
import orel.vpecherskii.minesweeper.config.Properties;
import orel.vpecherskii.minesweeper.model.MineSweeperModel;
import orel.vpecherskii.minesweeper.support.*;
import orel.vpecherskii.minesweeper.view.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
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
        Properties.countClosed=Properties.COL*Properties.COL;

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
                            GameState.gameState=GameState.LOSE;

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
                    JDialog win = new JDialog(){
                        @Override
                        public void paint(Graphics g) {
                            super.paint(g);
                            g.drawImage(new ImageLoader().loadImage("win"),0,30,this);
                        }
                    };
                    win.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            super.mouseClicked(e);
                            MinesweeperApp.restart();
                            win.dispose();
                        }
                    });
                    win.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                    win.setModal(true);
                    win.setSize(200,200);
                    win.setLocationRelativeTo(null);
                    win.setVisible(true);
                    panel.removeMouseListener(this);

//                for (int x = Properties.ROW/2-2;x<Properties.ROW/2+2;x++)
//                    for (int y = Properties.COL/2-2;y<Properties.COL/2+2;y++) {
//                        mineSweeperModel.getCellsArray().set(x, y, CellType.OPENED);
//                        mineSweeperModel.getCellsArrayUpper().set(x, y, CellType.OPENED);
//
//                    }
//                mineSweeperModel.getCellsArray().set(Properties.ROW/2-2, Properties.COL/2-2, CellType.WIN);
//                GameState.gameState = GameState.WIN;
//                panel.repaint();


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
