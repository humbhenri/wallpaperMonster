package com.humbertopinheiro.ui;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.humbertopinheiro.application.Application;
import com.humbertopinheiro.wallpaper.LoadingWallpaper;
import com.humbertopinheiro.wallpaper.Wallpaper;
import com.humbertopinheiro.wallpaper.WallpaperProvider;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.concurrent.Callable;
import java.util.logging.Logger;
import java.util.List;

import static com.humbertopinheiro.ui.PanelSideToPaint.*;

/**
 * Created with IntelliJ IDEA.
 * User: humberto
 * Date: 13/09/13
 * Time: 23:12
 */
public class WallpaperPanel extends JPanel implements MouseInputListener {

    private static final double SIDE_WIDTH_PERC = 0.1;

    private Image backgroundImage;

    private PanelSideToPaint panelSideToPaint = NONE;

    private WallpaperProvider wallpaperProvider;

    private LoadingWallpaper loadingWallpaper = new LoadingWallpaper();

    private List<WallpaperPanelEventListener> wallpaperPanelEventListenerList = Lists.newArrayList();

    private final static Logger LOGGER = Logger.getLogger(WallpaperPanel.class.getName());

    public WallpaperPanel(WallpaperProvider wallpaperProvider) {
        addMouseListener(this);
        addMouseMotionListener(this);
        this.wallpaperProvider = wallpaperProvider;
    }

    public WallpaperProvider getWallpaperProvider() {
        return wallpaperProvider;
    }

    public void setWallpaperProvider(WallpaperProvider wallpaperProvider) {
        this.wallpaperProvider = wallpaperProvider;
        paintNextWallpaper();
    }

    public void addWallpaperPanelEventListener(WallpaperPanelEventListener listener) {
        wallpaperPanelEventListenerList.add(listener);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (insideLeftSide(e.getX(), e.getY()) && wallpaperProvider.hasPrevious()) {
            paintPreviousWallpaper();
        } else if (insideRightSide(e.getX(), e.getY()) && wallpaperProvider.hasNext()) {
            paintNextWallpaper();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (mouseEnteredLeftSide(e.getX(), e.getY())) {
            panelSideToPaint = LEFT;
            repaint();
        } else if (mouseEnteredRightSide(e.getX(), e.getY())) {
            panelSideToPaint = RIGHT;
            repaint();
        } else if (mouseEnteredCenter(e.getX(), e.getY())) {
            panelSideToPaint = NONE;
            repaint();
        }
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

            paintSideShadow(g);
        }
    }

    private boolean mouseEnteredCenter(int x, int y) {
        return panelSideToPaint != NONE &&
                !(insideLeftSide(x, y) || insideRightSide(x, y));
    }

    private boolean mouseEnteredRightSide(int x, int y) {
        return panelSideToPaint != RIGHT && insideRightSide(x, y);
    }

    private boolean mouseEnteredLeftSide(int x, int y) {
        return panelSideToPaint != LEFT && insideLeftSide(x, y);
    }

    private boolean insideLeftSide(int x, int y) {
        return getLeftSide().contains(x, y);
    }

    private boolean insideRightSide(int x, int y) {
        return getRightSide().contains(x, y);
    }

    private Rectangle getLeftSide() {
        return new Rectangle(0, 0, (int) (getWidth() * SIDE_WIDTH_PERC), getHeight());
    }

    private Rectangle getRightSide() {
        return new Rectangle((int) (getWidth() * (1.0 - SIDE_WIDTH_PERC)), 0,
                (int) (getWidth() * SIDE_WIDTH_PERC), getHeight());
    }

    private void paintSideShadow(Graphics g) {
        Rectangle side = null;
        String text = "";
        if (wallpaperProvider.hasPrevious() && panelSideToPaint == LEFT) {
            side = getLeftSide();
            text = "PREVIOUS";
        } else if (wallpaperProvider.hasNext() && panelSideToPaint == RIGHT) {
            side = getRightSide();
            text = "NEXT";
        }
        if (side != null) {
            g.setColor(new Color(0, 0, 0, 0.5f));
            g.fillRect(side.x, side.y, side.width, side.height);
            drawSimpleString(g, text, side.width, side.x, side.y);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        } else {
            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    }

    private void drawSimpleString(Graphics g, String s, int width, int x, int y){
        int stringLen = (int)
                g.getFontMetrics().getStringBounds(s, g).getWidth();
        int start = width/2 - stringLen/2;
        g.drawString(s, start + x, y);
    }

    private void paintNextWallpaper() {
        paintFutureWallpaper(Application.INSTANCE.pool().submit(new Callable<Wallpaper>() {
            @Override
            public Wallpaper call() throws Exception {
                return wallpaperProvider.nextWallpaper();
            }
        }));
    }

    private void paintPreviousWallpaper() {
        paintFutureWallpaper(Application.INSTANCE.pool().submit(new Callable<Wallpaper>() {
            @Override
            public Wallpaper call() throws Exception {
                return wallpaperProvider.previousWallpaper();
            }
        }));
    }

    private void paintFutureWallpaper(ListenableFuture<Wallpaper> futureWallpaper) {
        paintWallpaper(loadingWallpaper);
        paintWallpaperWhenReady(futureWallpaper);
    }

    private void paintWallpaperWhenReady(ListenableFuture<Wallpaper> future) {
        Futures.addCallback(future, new FutureCallback<Wallpaper>() {
            @Override
            public void onSuccess(Wallpaper wallpaper) {
                paintWallpaper(wallpaper);
            }

            @Override
            public void onFailure(Throwable throwable) {
                LOGGER.severe(throwable.getMessage());
            }
        });
    }

    private void paintWallpaper(Wallpaper wallpaper) {
        backgroundImage = getToolkit().createImage(wallpaper.getFilename());
        for (WallpaperPanelEventListener listener : wallpaperPanelEventListenerList) {
            listener.wallpaperUpdated(wallpaper);
        }
        repaint();
    }

}
