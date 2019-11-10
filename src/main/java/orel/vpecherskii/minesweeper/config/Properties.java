package orel.vpecherskii.minesweeper.config;

public class Properties {
    public static final String SETTINGS_IMG = "settings";
    public static final String ICON_IMG = "icon";
    public static final String SETTINGS_LABEL = "Settings";
    public static final String SETTINGS_DIFFICULTY_LABEL_EASY = "Easy";
    public static final String SETTINGS_DIFFICULTY_LABEL_HARD = "Hard";
    private static Properties instance;
    public static final String WINDOW_NAME = "Minesweeper";
    public static int ROW = 10;
    public static  int COL = 10;
    public static int totalBombs = 10;
    public static int totalFlags = totalBombs;
    public static final int IMG_SIZE = 50;
    public static final int MENUHIN = 40;
    public static  int countClosed;


    public static Properties getInstance() {
        if (instance == null) {
            instance = new Properties();
        }
        return instance;
    }

    private Properties() {

    }


}



