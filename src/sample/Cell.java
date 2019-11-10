package sample;

import javax.swing.*;
import java.awt.*;

public class Cell extends JFrame
{
    int x;
    int y;
    Statement statement;

    public void setImage(Image image) {
        image = Statement.getImage(statement.toString());
    }

    public Image getImage() {
        return image;
    }

    public Image image;
    Cell(int x, int y, Statement statement)
    {
        this.x = x;
        this.y = y;
        this.statement = statement;
        image = Statement.getImage(statement.toString());

    }
    void setStatement(Statement statement){
        this.statement = statement;
        image = Statement.getImage(statement.toString());



    }


}
