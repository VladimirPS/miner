package sample;

import javax.swing.*;
import java.awt.*;

public class Field {
    private JPanel panel;
    private JPanel menu;
    private JFrame minerWindow;
    private Properties properties;

    Field() {

        properties = new Properties();
        initMenuPanel(properties);
        initPanel(properties);
        initMinerWindow();
    }
    private void initPanel(Properties properties) {
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (int x = 0; x < properties.ROW; x++)
                    for (int y = 0; y < properties.COL; y++)
                        g.drawImage(new Cell(1,2,Statement.ONE).statement.image, x * properties.IMG_SIZE, y * properties.IMG_SIZE, this);
            }
        };
        panel.setPreferredSize(new Dimension(properties.ROW * properties.IMG_SIZE, properties.COL * properties.IMG_SIZE));
    }
    private void initMenuPanel(Properties properties){
        menu = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(new ImageIcon("img/" + Statement.ICON.toString()+ ".png").getImage(),0,0,this);
            }
        };
        menu.setPreferredSize(new Dimension(properties.IMG_SIZE, properties.MENUHIN));
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


