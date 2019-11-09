package sample;

import java.awt.*;

public class Properties {

    int totalBombs;
    int totalFlags;
    public static int ROW;
    public static int COL;
    public static int IMG_SIZE = 50;

    public int getMENUHEIGHT() {
        return MENUHEIGHT;
    }

    private final int MENUHEIGHT = 50;

    public Properties(int ROW, int COL, int totalBombs) {
        this.ROW = ROW;
        this.COL = COL;
        this.totalBombs = totalBombs;
        this.totalFlags = totalBombs;
    }


}
