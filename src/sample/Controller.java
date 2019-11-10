package sample;

import com.sun.prism.Image;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Controller implements MouseListener, MouseMotionListener {

    CellsArray cellsArray;
    JPanel panel;
    Controller(CellsArray cellsArray, JPanel panel){
        this.cellsArray = cellsArray;
        this.panel = panel;

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for (Cell[] cells : cellsArray.getCellsArray()) {
            System.out.println("");
            for (Cell cell1 : cells) {
                System.out.print(cell1.statement + "\t");
            }
        }   new BombGenerator(cellsArray);
            panel.repaint();
        for (Cell[] cells : cellsArray.getCellsArray()) {
            System.out.println("");
            for (Cell cell1 : cells) {
                System.out.print(cell1.statement + "\t");
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
