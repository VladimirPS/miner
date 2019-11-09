package sample;

import javax.swing.*;
import java.awt.*;

public class Cell extends JFrame
{
    int x;
    int y;
    Statement statement;
    Cell(int x, int y, Statement statement)
    {
        this.x = x;
        this.y = x;
        this.statement = statement;
        ImageIcon icon = new ImageIcon("img/" + statement.toString() + ".png");
        this.statement.image = icon.getImage();

    }
}
