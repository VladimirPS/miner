package sample;

import javax.swing.*;
import java.awt.*;

public enum Statement {
    ZERO,
    ONE,
    TWO,
    THREE,
    FOUR,
    SIX,
    EIGHT,
    FLAG,
    BOMB,
    BOMBED,
    CLOSED,
    NOBOMB,
    MARK,
    OPENED,
    SETTINGS,
    ICON;

    public Statement statementInc(){
        return Statement.values()[this.ordinal()+1];
    }
    public static Image getImage(String s)
    {
        ImageIcon icon = new ImageIcon("img/" + s + ".png");
        return icon.getImage();
    }

}
