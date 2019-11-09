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
    ICON;

    public Image image;
    public Statement statementInc(){
        return Statement.values()[this.ordinal()+1];
    }

}
