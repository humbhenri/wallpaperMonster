package com.humbertopinheiro.application;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.humbertopinheiro.wallpaper.Wallpaper;
import com.humbertopinheiro.wallpaper.WallpaperProvider;

import java.nio.file.Path;
import java.util.logging.Logger;

import static com.google.common.util.concurrent.MoreExecutors.listeningDecorator;
import static java.util.concurrent.Executors.newFixedThreadPool;
import static java.util.logging.Logger.getLogger;

/**
 * Created with IntelliJ IDEA.
 * User: humberto
 * Date: 12/09/13
 * Time: 22:33
 */
public enum Application {

    INSTANCE;

    private WallpaperProvider[] wallpaperProviders;
    private String wallpaperStore;
    private ListeningExecutorService pool = listeningDecorator(newFixedThreadPool(10));
    private WallpaperSaver wallpaperSaver;
    private final static Logger LOGGER = getLogger(Application.class.getName());

    Application() {
        final String OS = SystemProperties.INSTANCE.getOS().toLowerCase();
        if (OS.indexOf("mac") >= 0) {
            wallpaperSaver = new MacWallpaperSaver();
        } else if (OS.indexOf("win") >= 0) {
        	wallpaperSaver = new WinWallpaperSaver();
        }
        else {
            wallpaperSaver = new WallpaperSaver() {
                @Override
                protected String getUserPathImagesFolder() {
                    return null;
                }
                @Override
                protected void setWallpaperBackground(Path path) {
                    LOGGER.info("Not implemented for this operating system.");
                }
            };
        }
    }

    public Application setWallpaperProviders(WallpaperProvider[] providers) {
        this.wallpaperProviders = providers;
        return INSTANCE;
    }

    public WallpaperProvider[] getWallpaperProviders() {
        return wallpaperProviders;
    }

    public String getWallpaperStore() {
        return wallpaperStore;
    }

    public Application setWallpaperStore(String wallpaperStore) {
        this.wallpaperStore = wallpaperStore;
        return INSTANCE;
    }

    public ListeningExecutorService pool() {
        return pool;
    }

    public void saveWallpaper(Wallpaper wallpaper) {
        wallpaperSaver.saveAsBackground(wallpaper);
    }
}
