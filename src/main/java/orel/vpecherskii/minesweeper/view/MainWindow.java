package orel.vpecherskii.minesweeper.view;

import orel.vpecherskii.minesweeper.config.Properties;
import orel.vpecherskii.minesweeper.controller.ActionsController;
import orel.vpecherskii.minesweeper.model.MineSweeperModel;
import orel.vpecherskii.minesweeper.support.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import static orel.vpecherskii.minesweeper.MinesweeperApp.restart;

public class MainWindow extends JFrame {
    private JPanel panel;
    private JPanel menu;
    private ImageLoader imageLoader = new ImageLoader();
    private JMenuBar menuBar;
    private MineSweeperModel model;

    public MainWindow(MineSweeperModel mineSweeperModel) {
        super(Properties.WINDOW_NAME);
        model = mineSweeperModel;
        this.initMenuBar();
        this.initMenuPanel();
        this.initGameFieldPanel(mineSweeperModel);
        this.initMinerWindow();
        this.panel.addMouseListener(new ActionsController(panel, mineSweeperModel));
        this.initMinerWindow();
    }


    private void initMenuPanel() {
        menu = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon i = new ImageIcon(imageLoader.loadImage(Properties.SETTINGS_IMG));
                g.drawImage(i.getImage(), -1, 0, this);
                g.drawImage(i.getImage(), 251, 0, this);
            }
        };
        menu.setPreferredSize(new Dimension(Properties.IMG_SIZE, Properties.MENUHIN));
    }

    private void initMenuBar() {
        this.menuBar = new JMenuBar();
        JMenu menu = new JMenu(Properties.SETTINGS_LABEL);
        JMenuItem item1 = new JMenuItem(Properties.SETTINGS_DIFFICULTY_LABEL_EASY);
        JMenuItem item2 = new JMenuItem(Properties.SETTINGS_DIFFICULTY_LABEL_SUPER_HARD_VOVA);
        item1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Properties.COL = 10;
                Properties.ROW = 10;
                System.out.println("Changed Settings to EASY");
                restart();
                //Add Game restart here
            }
        });

        item2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Properties.COL = 20;
                Properties.ROW = 30;
                System.out.println("Changed Settings to VOLOGHEA");
                //Add Game restart here
                restart();
            }
        });
        menu.add(item1);
        menu.add(item2);
        menuBar.add(menu);
    }

    private void initGameFieldPanel(MineSweeperModel mineSweeperModel) {
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (int x = 0; x < Properties.ROW; x++)
                    for (int y = 0; y < Properties.COL; y++) {
                        System.out.println("x:" + x + " y:" + y);
                        g.drawImage(
                                mineSweeperModel.getCellsArray().getCellsArray()[x][y].getImage(),
                                x * Properties.IMG_SIZE, y * Properties.IMG_SIZE,
                                this
                        );
                        g.drawImage(
                                mineSweeperModel.getCellsArrayUpper().getCellsArray()[x][y].getImage(),
                                x * Properties.IMG_SIZE, y * Properties.IMG_SIZE,
                                this
                        );


                    }
            }
        };
        panel.setPreferredSize(new Dimension(Properties.ROW * Properties.IMG_SIZE,
                Properties.COL * Properties.IMG_SIZE));


    }

    private void initMinerWindow() {
        this.setJMenuBar(menuBar);
        this.add(menu, BorderLayout.PAGE_START);
        this.add(this.panel);
        this.pack();
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setIconImage(imageLoader.loadImage(Properties.ICON_IMG));
        this.setVisible(true);
    }


}
