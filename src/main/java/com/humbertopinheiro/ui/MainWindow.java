package com.humbertopinheiro.ui;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import net.miginfocom.swing.MigLayout;

import org.apache.commons.lang3.StringUtils;

import com.humbertopinheiro.application.SystemProperties;
import com.humbertopinheiro.platform.LinuxWallpaperSaverFactory;
import com.humbertopinheiro.platform.WallpaperSaverFactory;
import com.humbertopinheiro.wallpaper.EmptyWallpaperProvider;
import com.humbertopinheiro.wallpaper.Wallpaper;
import com.humbertopinheiro.wallpaper.WallpaperProvider;
import com.humbertopinheiro.wallpaper.WallpaperProviders;

/**
 * Created with IntelliJ IDEA. User: humberto Date: 05/09/13 Time: 23:01
 */
public class MainWindow extends JFrame implements WallpaperPanelEventListener {

	private static final long serialVersionUID = 1L;

	private final WallpaperPanel wallpaperPanel;

	private final WallpaperProviders wallpaperProviders = new WallpaperProviders();

	private WallpaperSaverFactory wallpaperSaverFactory;

	public MainWindow(WallpaperPanel wallpaperPanel) {
		setTitle("wallpaperMonster");
		Container pane = getContentPane();
		pane.setLayout(new MigLayout("", "[grow]", "[grow]"));
		JPanel header = new JPanel(new MigLayout());
		header.add(getInfoLabel());
		header.add(getProvidersList());
		pane.add(header, "dock north");
		this.wallpaperPanel = wallpaperPanel;
		wallpaperPanel.addWallpaperPanelEventListener(this);
		pane.add(wallpaperPanel, "grow");
		setSize(new Dimension(1024, 768));
	}

	private Component getProvidersList() {
		final JComboBox<WallpaperProvider> wallpaperProviderJComboBox = new JComboBox<>(
				wallpaperProviders.getWallpaperProviders());
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
		String os = SystemProperties.instance().getOS().toLowerCase();
		getWallpaperSaverFactory().getWallpaperSaver(os)
				.setWallpaperBackground(new File(wallpaper.getFilename()));
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
		MainWindow mainWindow = new MainWindow(new WallpaperPanel(
				new EmptyWallpaperProvider()));
		WallpaperSaverFactory wallpaperSaverFactory = new WallpaperSaverFactory();
		if (StringUtils.indexOfIgnoreCase(SystemProperties.instance().getOS(),
				"linux") > 0) {
			wallpaperSaverFactory = new LinuxWallpaperSaverFactory();
		}
		mainWindow.setWallpaperSaverFactory(wallpaperSaverFactory);
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setVisible(true);
	}

	public WallpaperSaverFactory getWallpaperSaverFactory() {
		return wallpaperSaverFactory;
	}

	public void setWallpaperSaverFactory(
			WallpaperSaverFactory wallpaperSaverFactory) {
		this.wallpaperSaverFactory = wallpaperSaverFactory;
	}
}
