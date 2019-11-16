package sample;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Field {
    private JPanel panel;
    private JPanel menu;
    private JFrame minerWindow;
    public Properties properties;
    Controller controller;


    Field() {
        this.properties = new Properties();
        Generator generator = new Generator(properties);
        initMenuPanel(properties);
        initPanel(properties, generator);
        initMinerWindow();
        panel.addMouseListener(controller = new Controller(generator.cellsArray, panel, generator.cellsArrayUpper,generator,properties));

    }

    private void initPanel(Properties properties, Generator generator) {
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (int x = 0; x < Properties.ROW; x++)
                    for (int y = 0; y < Properties.COL; y++) {
                        g.drawImage(generator.cellsArray.getCellsArray()[x][y].image,
                                x * properties.IMG_SIZE, y * properties.IMG_SIZE, this);
                        g.drawImage(generator.cellsArrayUpper.getCellsArray()[x][y].image,
                                x * properties.IMG_SIZE, y * properties.IMG_SIZE, this);
                    }
            }
        };
        panel.setPreferredSize(new Dimension(properties.ROW * properties.IMG_SIZE,
                properties.COL * properties.IMG_SIZE));

    }

    private void initMenuPanel(Properties properties) {
        menu = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(new ImageIcon("img/" + Statement.SETTINGS.toString() + ".png").getImage(), -1, 0, this);
                g.drawImage(new ImageIcon("img/" + Statement.SETTINGS.toString() + ".png").getImage(), 251, 0, this);

            }
        };
        menu.setPreferredSize(new Dimension(properties.IMG_SIZE, properties.MENUHIN));
    }

    private void initMinerWindow() {
        minerWindow = new JFrame("Miner");
        minerWindow.add(menu, BorderLayout.PAGE_START);
        minerWindow.add(this.panel);
        minerWindow.pack();
        minerWindow.setVisible(true);
        minerWindow.setResizable(false);
        minerWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        minerWindow.setLocationRelativeTo(null);
        minerWindow.setIconImage(Statement.getImage("ICON"));
    }

}



