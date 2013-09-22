package com.humbertopinheiro.ui;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.humbertopinheiro.application.Application;
import com.humbertopinheiro.wallpaper.EarthPornSite;
import com.humbertopinheiro.wallpaper.Wallpaper;
import com.humbertopinheiro.wallpaper.WallpaperProvider;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Callable;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: humberto
 * Date: 05/09/13
 * Time: 23:01
 */
public class MainWindow extends JFrame {

    private final WallpaperPanel wallpaperPanel;
    private final static Logger LOGGER = Logger.getLogger(EarthPornSite.class.getName());

    public MainWindow() {
        setTitle("wallpaperMonster");
        Container pane = getContentPane();
        pane.setLayout(new MigLayout("", "[grow]", "[grow]"));
        JPanel header = new JPanel(new MigLayout());
        header.add(getInfoLabel());
        header.add(getProvidersList());
        pane.add(header, "dock north");
        wallpaperPanel = new WallpaperPanel();
        pane.add(wallpaperPanel, "grow");
        setSize(new Dimension(1024, 768));
    }

    private Component getProvidersList() {
        WallpaperProvider[] providers = Application.INSTANCE.getWallpaperProviders();
        final JComboBox<WallpaperProvider> wallpaperProviderJComboBox = new JComboBox<>(providers);
        wallpaperProviderJComboBox.setName("wallpaperProviders");
        wallpaperProviderJComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListenableFuture<Wallpaper> future = Application.INSTANCE.pool().submit(new Callable<Wallpaper>() {
                    @Override
                    public Wallpaper call() throws Exception {
                        WallpaperProvider provider = (WallpaperProvider) wallpaperProviderJComboBox.getSelectedItem();
                        return provider.nextWallpaper();
                    }
                });
                Futures.addCallback(future, new FutureCallback<Wallpaper>() {
                    @Override
                    public void onSuccess(Wallpaper wallpaper) {
                        setTitle(wallpaper.getTitle());
                        wallpaperPanel.setWallpaper(wallpaper);
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        LOGGER.severe(throwable.getMessage());
                    }
                });

            }
        });
        return wallpaperProviderJComboBox;
    }

    private Component getInfoLabel() {
        JLabel label = new JLabel("Choose wallpaper provider");
        label.setName("info");
        return label;
    }

    public static void main(String... args) {
        setupApplication();
        MainWindow mainWindow = new MainWindow();
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setVisible(true);
    }

    private static void setupApplication() {
        WallpaperProvider[] providers = {new EarthPornSite()};
        Application.INSTANCE.setWallpaperProviders(providers)
                .setWallpaperStore("/tmp/");
    }
}
