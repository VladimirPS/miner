package orel.vpecherskii.minesweeper;

import orel.vpecherskii.minesweeper.model.MineSweeperModel;
import orel.vpecherskii.minesweeper.view.MainWindow;

public class MinesweeperApp {

    private static MineSweeperModel mineSweeperModel = new MineSweeperModel();
    private static MainWindow mainWindow;

    public static void main(String[] args) {
        new MinesweeperApp();
    }

    private MinesweeperApp() {
        this.mainWindow = new MainWindow(mineSweeperModel);
    }

    public static void restart() {
        mainWindow.dispose();
        mineSweeperModel.reInitialize();
        mainWindow = new MainWindow(mineSweeperModel);
    }
}



