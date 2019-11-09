package sample;

import java.awt.*;

public class Properties {

    static int totalBombs;
    static int totalFlags;
    public static int ROW;
    public static int COL;
    public final int IMG_SIZE = 50;
    public final int MENUHIN = 50;
    public Properties() {
        ROW = 10;
        COL = 10;
        totalFlags = 10;
        totalBombs = totalFlags;
    }
    void setTotalBombsAndFlags(int x){
        totalBombs = x;
        totalFlags = totalBombs;
    }
    int getTotalBombs(){
        return totalBombs;
    }
    public static int getTotalFlags() {
        return totalFlags;
    }

}
