package sample;

import com.sun.prism.Image;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Controller implements MouseListener, MouseMotionListener {

    CellsArray cellsArray;
    CellsArray cellsArrayUpper;
    JPanel panel;
    private Generator generator;
    private Properties properties;


    Controller(CellsArray cellsArray, JPanel panel, CellsArray cellsArrayUpper, Generator generator, Properties properties){
        this.cellsArray = cellsArray;
        this.panel = panel;
        this.cellsArrayUpper = cellsArrayUpper;
        int click;
        this.generator= generator;
        this.properties=properties;

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (Properties.countClosed==Properties.ROW*Properties.COL){
           new BombGenerator(cellsArray,e.getX()/50,e.getY()/50);
            cellsArrayUpper.set(e.getX()/50,e.getY()/50, Statement.OPENED);
            Properties.countClosed--;
            panel.repaint();
        }
        if (e.getButton() == MouseEvent.BUTTON1) {
            if (generator.cellsArrayUpper.getCell(e.getX() / 50, e.getY() / 50).statement != Statement.FLAG) {
                generator.cellsArrayUpper.set(e.getX() / 50, e.getY() / 50, Statement.OPENED);

                if (generator.cellsArray.getCell(e.getX() / 50, e.getY() / 50).statement == Statement.BOMB) {
                    generator.cellsArray.set(e.getX() / 50, e.getY() / 50, Statement.BOMBED);
                    for (int x = 0; x < Properties.ROW; x++)
                        for (int y = 0; y < Properties.COL; y++)
                            if (generator.cellsArrayUpper.getCell(x, y).statement == Statement.FLAG &
                                    generator.cellsArray.getCell(x, y).statement == Statement.BOMB)
                                generator.cellsArrayUpper.set(e.getX() / 50, e.getY() / 50, Statement.FLAG);
                            else
                                generator.cellsArrayUpper.getCell(x, y).setStatement(Statement.OPENED);

                }
                if (generator.cellsArray.getCell(e.getX() / 50, e.getY() / 50).statement == Statement.ZERO) {
                    properties.aroundZero(e.getX() / 50, e.getY() / 50, generator.cellsArrayUpper, generator.cellsArray);
                    panel.repaint();
                }
                panel.repaint();
            }
        }
        if (e.getButton() == MouseEvent.BUTTON3) {
            switch (generator.cellsArrayUpper.getCell(e.getX() / 50, e.getY() / 50).statement) {
                case FLAG:
                    generator.cellsArrayUpper.set(e.getX() / 50, e.getY() / 50, Statement.CLOSED);
                    panel.repaint();
                    break;
                case CLOSED:
                    generator.cellsArrayUpper.set(e.getX() / 50, e.getY() / 50, Statement.FLAG);
                    panel.repaint();
                    break;
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
