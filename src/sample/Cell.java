package sample;

import sun.misc.Launcher;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class Cell extends JFrame
{
    int x;
    int y;
    Properties properties;


    public Statement getStatement() {
        return statement;
    }

    Statement statement;

//    public void setImage(Image image) {
//        image = getImage(statement.toString());
//    }

    public Image getImage() {
        return image;
    }

    public Image image;
    Cell(int x, int y, Statement statement, Properties properties)  {
        this.x = x;
        this.y = y;
        this.statement = statement;
        this.properties = properties;
        image = properties.getImage1(statement.toString());


    }
    void setStatement(Statement statement){
        this.statement = statement;
        image = properties.getImage1(statement.toString());

    }

    public Statement statementInc(){
        this.statement= statement.values()[statement.ordinal()+1];
        return this.statement;
    }


}
