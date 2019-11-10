package sample;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Properties extends JFrame {

    static int totalBombs;
    static int totalFlags;
    public static int ROW;
    public static int COL;
    public final int IMG_SIZE = 50;
    public final int MENUHIN = 40;
    public Properties() {
        ROW = 10;
        COL = 10;
        totalFlags = 15;
        totalBombs = 12;
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
    static Boolean notOut(int x, int y){
        return x>=0&&x<COL&&y>=0&&y<ROW;
    }

}
