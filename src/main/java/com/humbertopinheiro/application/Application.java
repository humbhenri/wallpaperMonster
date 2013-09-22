package com.humbertopinheiro.application;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.humbertopinheiro.wallpaper.WallpaperProvider;

import java.util.concurrent.Executors;

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
    private ListeningExecutorService pool =
            MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));

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
}
