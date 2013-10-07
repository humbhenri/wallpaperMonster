package com.humbertopinheiro.ui;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.ListenableFuture;
import com.humbertopinheiro.application.Application;
import com.humbertopinheiro.wallpaper.LoadingWallpaper;
import com.humbertopinheiro.wallpaper.Wallpaper;
import com.humbertopinheiro.wallpaper.WallpaperProvider;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.logging.Logger;

import static ch.lambdaj.Lambda.forEach;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.util.concurrent.Futures.addCallback;
import static com.humbertopinheiro.ui.PanelSideToPaint.*;
import static java.util.logging.Logger.getLogger;

/**
 * Created with IntelliJ IDEA.
 * User: humberto
 * Date: 13/09/13
 * Time: 23:12
 */
public class WallpaperPanel extends JPanel implements MouseInputListener {

	private static final long serialVersionUID = 1L;

	private static final double SIDE_WIDTH_PERC = 0.1;

    private Image backgroundImage;

    private PanelSideToPaint panelSideToPaint = NONE;

    private WallpaperProvider wallpaperProvider;

    private LoadingWallpaper loadingWallpaper = new LoadingWallpaper();

    private Wallpaper currentWallpaper;

    private List<WallpaperPanelEventListener> wallpaperPanelEventListenerList = newArrayList();

    private final static Logger LOGGER = getLogger(WallpaperPanel.class.getName());

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
        if (e.getButton() == MouseEvent.BUTTON3) {
            JPopupMenu menu = new JPopupMenu ();
            JMenuItem menuItem = new JMenuItem("Save as wallpaper");
            menuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    forEach(wallpaperPanelEventListenerList).wallpaperSelected(currentWallpaper);
                }
            });
            menu.add(menuItem);
            menu.show(this, e.getX(), e.getY());
        }
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
        if (wallpaperProvider == null) {
            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        } else if (mouseEnteredLeftSide(e.getX(), e.getY()) && wallpaperProvider.hasPrevious()) {
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        } else if (mouseEnteredRightSide(e.getX(), e.getY()) && wallpaperProvider.hasNext()) {
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        } else {
            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g.create();
        if (backgroundImage != null) {
            int width = backgroundImage.getWidth(this);
            int height = backgroundImage.getHeight(this);
            graphics.drawImage(backgroundImage,
                    (getWidth() / 2) - (width / 2),
                    (getHeight() / 2) - (height / 2),
                    this);

            paintSideShadow(graphics);
        }
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

    private void paintSideShadow(Graphics2D g) {
        if (currentWallpaper == loadingWallpaper) {
            return;
        }
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        if (wallpaperProvider.hasPrevious()) {
            paintSide(g, getLeftSide(), "<");
        }
        if (wallpaperProvider.hasNext()) {
            paintSide(g, getRightSide(), ">");
        }
    }

    private void paintSide(Graphics2D g, Rectangle rectangle, String text) {
        g.setColor(new Color(0, 0, 0, 0.5f));
        g.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Dialog", Font.BOLD, 48));
        FontMetrics fm = g.getFontMetrics();
        int totalWidth = (fm.stringWidth(text) * 2) + 4;
        int x = rectangle.x + (rectangle.width - totalWidth) / 2;
        int y = (rectangle.height - fm.getHeight()) / 2;
        g.drawString(text, x, y + ((fm.getDescent() + fm.getAscent()) / 2));
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
        addCallback(futureWallpaper, new FutureCallback<Wallpaper>() {
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
        forEach(wallpaperPanelEventListenerList).wallpaperUpdated(wallpaper);
        currentWallpaper = wallpaper;
        repaint();
    }

}
