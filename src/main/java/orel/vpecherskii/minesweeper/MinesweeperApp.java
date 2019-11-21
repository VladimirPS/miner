package orel.vpecherskii.minesweeper;

import orel.vpecherskii.minesweeper.config.Properties;
import orel.vpecherskii.minesweeper.model.MineSweeperModel;
import orel.vpecherskii.minesweeper.view.MainWindow;

public class MinesweeperApp {

    private MineSweeperModel mineSweeperModel = new MineSweeperModel();
    private MainWindow mainWindow;

    public static void main(String[] args) {
        new MinesweeperApp();
    }

    private MinesweeperApp() {
        this.mainWindow = new MainWindow(mineSweeperModel);
    }
}



