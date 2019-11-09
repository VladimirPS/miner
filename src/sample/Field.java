package sample;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

public class Field
{
    Field()
    {
        Properties properties = new Properties(100,100,10);
        Cell cell = new Cell(Statement.BOMB);
        JFrame minerWindow = new JFrame("Miner") {
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                g.drawImage(cell.getImage(),0,0,this);
            }
        };
        minerWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        minerWindow.setSize(100,100);
        minerWindow.setLocationRelativeTo(null);
        minerWindow.setVisible(true);
    }



}


