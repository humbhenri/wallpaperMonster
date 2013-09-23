package com.humbertopinheiro.wallpaper;

/**
 * Created with IntelliJ IDEA.
 * User: humberto
 * Date: 03/09/13
 * Time: 20:43
 */
public interface WallpaperProvider {
    Wallpaper nextWallpaper();
    Wallpaper previousWallpaper();
    boolean hasPrevious();
    boolean hasNext();
}
