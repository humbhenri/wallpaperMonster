package com.humbertopinheiro.wallpaper;

/**
 * Created with IntelliJ IDEA.
 * User: humberto
 * Date: 12/09/13
 * Time: 22:35
 */
public class MockWallpaperProvider implements WallpaperProvider {
    @Override
    public Wallpaper nextWallpaper() {
        return new Wallpaper(MockWallpaperProvider.class.getResource("/sample.jpg"), null);
    }
}
