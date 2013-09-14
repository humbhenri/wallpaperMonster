package com.humbertopinheiro.ui;

import com.humbertopinheiro.application.Application;
import com.humbertopinheiro.wallpaper.EarthPornSite;
import com.humbertopinheiro.wallpaper.Wallpaper;
import com.humbertopinheiro.wallpaper.WallpaperProvider;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: humberto
 * Date: 05/09/13
 * Time: 23:01
 */
public class MainWindow extends JFrame {

    public MainWindow() {
        setTitle("wallpaperMonster");
        Container pane = getContentPane();
        pane.setLayout(new MigLayout("", "[grow]", "[grow]"));
        Box header = Box.createVerticalBox();
        header.add(getInfoLabel());
        header.add(getProvidersList());
        pane.add(header, "dock north");
        pane.add(getWallpaperArea(), "grow");
        setSize(new Dimension(1024, 768));
    }

    private Component getWallpaperArea() {
        JPanel jPanel = new JPanel();
        return jPanel;
    }

    private Component getProvidersList() {
        WallpaperProvider[] providers = Application.INSTANCE.getWallpaperProviders();
        final JComboBox <WallpaperProvider> wallpaperProviderJComboBox = new JComboBox<>(providers);
        wallpaperProviderJComboBox.setName("wallpaperProviders");
        wallpaperProviderJComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WallpaperProvider provider = (WallpaperProvider) wallpaperProviderJComboBox.getSelectedItem();
                Wallpaper wallpaper = provider.nextWallpaper();
                setTitle(wallpaper.getTitle());
            }
        });
        return wallpaperProviderJComboBox;
    }

    private Component getInfoLabel() {
        JLabel label = new JLabel("Choose wallpaper provider");
        label.setName("info");
        label.setFont(new Font("Sans Serif", Font.PLAIN, 24));
        label.setHorizontalAlignment(JLabel.CENTER);
        return label;
    }

    public static void main(String... args) {
        setupApplication();
        MainWindow mainWindow = new MainWindow();
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setVisible(true);
    }

    private static void setupApplication() {
        WallpaperProvider[] providers = { new EarthPornSite() };
        Application.INSTANCE.setWallpaperProviders(providers);
    }
}
