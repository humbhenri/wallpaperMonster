package com.humbertopinheiro.ui;

import static ch.lambdaj.Lambda.forEach;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.util.concurrent.Futures.addCallback;
import static com.humbertopinheiro.base.Side.LEFT;
import static com.humbertopinheiro.base.Side.NONE;
import static com.humbertopinheiro.base.Side.RIGHT;
import static java.util.logging.Logger.getLogger;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.logging.Logger;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.ListenableFuture;
import com.humbertopinheiro.base.Side;
import com.humbertopinheiro.wallpaper.FutureWallpaperProvider;
import com.humbertopinheiro.wallpaper.LoadingWallpaper;
import com.humbertopinheiro.wallpaper.Wallpaper;
import com.humbertopinheiro.wallpaper.WallpaperProvider;

/**
 * Created with IntelliJ IDEA. User: humberto Date: 13/09/13 Time: 23:12
 */
public class WallpaperPanel extends MouseListenerPanel {

	private static final long serialVersionUID = 1L;

	private final static Logger LOGGER = getLogger(WallpaperPanel.class
			.getName());

	private final Side panelSideToPaint = NONE;

	private WallpaperProvider wallpaperProvider;

	private final LoadingWallpaper loadingWallpaper = new LoadingWallpaper();

	private Wallpaper currentWallpaper;

	private final List<WallpaperPanelEventListener> wallpaperPanelEventListenerList = newArrayList();

	private Image image;

	private final Bounds bounds;

	private final FutureWallpaperProvider futureWallpaperProvider = new FutureWallpaperProvider();

	public WallpaperPanel(WallpaperProvider wallpaperProvider) {
		addMouseListener(this);
		addMouseMotionListener(this);
		this.wallpaperProvider = wallpaperProvider;
		futureWallpaperProvider.setWallpaperProvider(wallpaperProvider);
		bounds = new Bounds(this);
	}

	public WallpaperProvider getWallpaperProvider() {
		return wallpaperProvider;
	}

	public void setWallpaperProvider(WallpaperProvider wallpaperProvider) {
		this.wallpaperProvider = wallpaperProvider;
		futureWallpaperProvider.setWallpaperProvider(wallpaperProvider);
		paintFutureWallpaper(futureWallpaperProvider
				.getFutureWallpaper(Side.RIGHT));
	}

	public void addWallpaperPanelEventListener(
			WallpaperPanelEventListener listener) {
		wallpaperPanelEventListenerList.add(listener);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		bounds.setMouse(e.getX(), e.getY());
		Side sideClicked = bounds.getSideClicked();
		paintFutureWallpaper(futureWallpaperProvider
				.getFutureWallpaper(sideClicked));
	}

	@Override
	public void mousePressed(MouseEvent e) {
		new RightClickMenu(this).show(e, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				forEach(wallpaperPanelEventListenerList).wallpaperSelected(
						currentWallpaper);
			}
		});
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		bounds.setMouse(e.getX(), e.getY());
		setCursor(new CursorIndicator().getCursor(
				wallpaperProvider.hasPrevious(), wallpaperProvider.hasNext(),
				mouseEnteredLeftSide(), mouseEnteredRightSide()));
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D graphics = (Graphics2D) g.create();
		Rectangle container = new Rectangle(getWidth(), getHeight());
		new BackgroundImage(this, image, container).draw(graphics);
		new NavigationIndicator(container).draw(graphics,
				currentWallpaper == loadingWallpaper,
				wallpaperProvider.hasPrevious(), wallpaperProvider.hasNext());
	}

	private boolean mouseEnteredRightSide() {
		return panelSideToPaint != RIGHT && bounds.insideRightSide();
	}

	private boolean mouseEnteredLeftSide() {
		return panelSideToPaint != LEFT && bounds.insideRightSide();
	}

	private void paintFutureWallpaper(
			ListenableFuture<Wallpaper> futureWallpaper) {
		if (futureWallpaper == null) {
			return;
		}
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
		image = getToolkit().createImage(wallpaper.getFilename());
		forEach(wallpaperPanelEventListenerList).wallpaperUpdated(wallpaper);
		currentWallpaper = wallpaper;
		repaint();
	}

}
