package com.humbertopinheiro.application;

import com.humbertopinheiro.wallpaper.WallpaperProvider;

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

    public void setWallpaperProviders(WallpaperProvider[] providers){
        this.wallpaperProviders = providers;
    }

    public WallpaperProvider[] getWallpaperProviders() {
        return wallpaperProviders;
    }

    public String getWallpaperStore() {
        return wallpaperStore;
    }

    public void setWallpaperStore(String wallpaperStore) {
        this.wallpaperStore = wallpaperStore;
    }
}
