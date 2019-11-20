package orel.vpecherskii.minesweeper.view;

import orel.vpecherskii.minesweeper.controller.ActionsController;
import orel.vpecherskii.minesweeper.support.CellType;
import orel.vpecherskii.minesweeper.config.Properties;
import orel.vpecherskii.minesweeper.model.FieldModel;
import orel.vpecherskii.minesweeper.support.GameState;

import javax.swing.*;
import java.awt.*;

public class Field {
    private JPanel panel;
    private JPanel menu;
    private JFrame minerWindow;
    private Properties properties = new Properties();
    private ActionsController actionsController;



    public Field() {
        initialize();

    }

    private void initialize() {
        FieldModel fieldModel = new FieldModel(properties);
        this.initMenuPanel(properties);
        this.initPanel(properties, fieldModel);
        this.initMinerWindow();
        panel.addMouseListener(actionsController = new ActionsController( panel, fieldModel, properties));

    }

    private void initPanel(Properties properties, FieldModel fieldModel) {
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (int x = 0; x < properties.ROW; x++)
                    for (int y = 0; y < properties.COL; y++) {
                        //rem first call

                        g.drawImage(
                                fieldModel.getCellsArray().getCellsArray()[x][y].image,
                                x * properties.IMG_SIZE, y * properties.IMG_SIZE,
                                this
                        );
                        g.drawImage(
                                fieldModel.getCellsArrayUpper().getCellsArray()[x][y].image,
                                x * properties.IMG_SIZE, y * properties.IMG_SIZE,
                                this
                        );



            }}
        };
        panel.setPreferredSize(new Dimension(properties.ROW * properties.IMG_SIZE,
                properties.COL * properties.IMG_SIZE));


    }

    private void initMenuPanel(Properties properties) {
        menu = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(new ImageIcon(properties.getImage1("settings")).getImage(), -1, 0, this);
                g.drawImage(new ImageIcon(properties.getImage1("settings")).getImage(), 251, 0, this);

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
        minerWindow.setIconImage(properties.getImage1("icon"));
    }

}



