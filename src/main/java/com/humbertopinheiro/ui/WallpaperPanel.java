package com.humbertopinheiro.ui;

import com.humbertopinheiro.wallpaper.Wallpaper;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: humberto
 * Date: 13/09/13
 * Time: 23:12
 */
public class WallpaperPanel extends JPanel {

    private final static Logger LOGGER = Logger.getLogger(WallpaperPanel.class .getName());

    private Image backgroundImage;

    public void setWallpaper(Wallpaper wallpaper) {
        try {
            backgroundImage = ImageIO.read(new File(wallpaper.getFilename()));
            repaint();
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
            backgroundImage = null;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, this);
        }
    }
}
