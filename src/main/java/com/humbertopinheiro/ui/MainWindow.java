package com.humbertopinheiro.ui;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import net.miginfocom.swing.MigLayout;

import com.humbertopinheiro.application.Application;
import com.humbertopinheiro.application.SystemProperties;
import com.humbertopinheiro.wallpaper.EarthPornSite;
import com.humbertopinheiro.wallpaper.EmptyWallpaperProvider;
import com.humbertopinheiro.wallpaper.MockWallpaperProvider;
import com.humbertopinheiro.wallpaper.Wallbase;
import com.humbertopinheiro.wallpaper.Wallpaper;
import com.humbertopinheiro.wallpaper.WallpaperProvider;

/**
 * Created with IntelliJ IDEA. User: humberto Date: 05/09/13 Time: 23:01
 */
public class MainWindow extends JFrame implements WallpaperPanelEventListener {

	private static final long serialVersionUID = 1L;

	private final WallpaperPanel wallpaperPanel;

	public MainWindow() {
		setTitle("wallpaperMonster");
		Container pane = getContentPane();
		pane.setLayout(new MigLayout("", "[grow]", "[grow]"));
		JPanel header = new JPanel(new MigLayout());
		header.add(getInfoLabel());
		header.add(getProvidersList());
		pane.add(header, "dock north");
		wallpaperPanel = new WallpaperPanel(new EmptyWallpaperProvider());
		wallpaperPanel.addWallpaperPanelEventListener(this);
		pane.add(wallpaperPanel, "grow");
		setSize(new Dimension(1024, 768));
	}

	private Component getProvidersList() {
		final JComboBox<WallpaperProvider> wallpaperProviderJComboBox = new JComboBox<>(
				Application.INSTANCE.getWallpaperProviders());
		wallpaperProviderJComboBox.setName("wallpaperProviders");
		addChangeWallpaperListener(wallpaperProviderJComboBox);
		return wallpaperProviderJComboBox;
	}

	private void addChangeWallpaperListener(
			final JComboBox<WallpaperProvider> wallpaperProviderJComboBox) {
		wallpaperProviderJComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				wallpaperPanel
						.setWallpaperProvider((WallpaperProvider) wallpaperProviderJComboBox
								.getSelectedItem());
			}
		});
	}

	private Component getInfoLabel() {
		JLabel label = new JLabel("Choose wallpaper provider");
		label.setName("info");
		return label;
	}

	@Override
	public void wallpaperUpdated(final Wallpaper wallpaper) {
		setTitleFromWallpaper(wallpaper);
	}

	@Override
	public void wallpaperSelected(Wallpaper wallpaper) {
		Application.INSTANCE.saveWallpaper(wallpaper);
	}

	private void setTitleFromWallpaper(final Wallpaper wallpaper) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				setTitle(wallpaper.getTitle());
			}
		});
	}

	public static void main(String... args) {
		setupApplication();
		MainWindow mainWindow = new MainWindow();
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setVisible(true);
	}

	private static void setupApplication() {
		WallpaperProvider[] providers = { new EarthPornSite(),
				new MockWallpaperProvider(), new Wallbase() };
		Application.INSTANCE.setWallpaperProviders(providers)
				.setWallpaperStore(SystemProperties.INSTANCE.getTempDir());
	}
}
