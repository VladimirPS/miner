package orel.vpecherskii.minesweeper.support;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class ImageLoader {

    public Image loadImage(String s) {
        URL url = getClass().getResource("/img/" + s.toLowerCase() + ".png");
        ImageIcon icon = new ImageIcon(url);
        return icon.getImage();
    }
}
