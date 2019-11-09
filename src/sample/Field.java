package sample;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

public class Field {
    private JPanel panel;
    private JPanel menu;
    private JFrame minerWindow;

    Field() {
        initPanel();
        initMinerWindow();
    }
    private void initPanel() {
        Properties properties = new Properties(10, 10, 10);
        Cell cell = new Cell(0, 0, Statement.CLOSED);
        menu = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(new ImageIcon("img/" + Statement.BOMB.toString()+ ".png").getImage(),0,0,this);
            }
        };
        menu.setPreferredSize(new Dimension(properties.ROW*properties.IMG_SIZE, properties.getMENUHEIGHT()));
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (int x = 0; x < properties.ROW; x++)
                    for (int y = 0; y < properties.COL; y++)
                        g.drawImage(cell.getImage(), x * properties.IMG_SIZE, y * properties.IMG_SIZE, this);
            }
        };
        panel.setPreferredSize(new Dimension(properties.ROW * properties.IMG_SIZE, properties.COL * properties.IMG_SIZE));
    }
    private void initMinerWindow(){
        minerWindow = new JFrame("Miner");
        minerWindow.add(menu, BorderLayout.PAGE_START);
        minerWindow.add(this.panel);
        minerWindow.pack();
        minerWindow.setVisible(true);
        minerWindow.setResizable(false);
        minerWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        minerWindow.setLocationRelativeTo(null);

    }
}


