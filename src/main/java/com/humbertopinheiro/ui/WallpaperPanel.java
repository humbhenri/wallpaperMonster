package com.humbertopinheiro.ui;

import com.humbertopinheiro.wallpaper.Wallpaper;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: humberto
 * Date: 13/09/13
 * Time: 23:12
 */
public class WallpaperPanel extends JPanel {

    private Image backgroundImage;

    public void setWallpaper(Wallpaper wallpaper) {
        backgroundImage = getToolkit().createImage(wallpaper.getFilename());
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            int width = backgroundImage.getWidth(this);
            int height = backgroundImage.getHeight(this);
            g.drawImage(backgroundImage,
                    (getWidth() / 2) - (width / 2),
                    (getHeight() / 2) - (height / 2),
                    this);
        }
    }
}
