package com.humbertopinheiro.wallpaper;

import com.humbertopinheiro.utils.FileUtils;
import com.humbertopinheiro.utils.URLUtils;

/**
 * Created with IntelliJ IDEA.
 * User: humberto
 * Date: 12/09/13
 * Time: 22:35
 */
public class MockWallpaperProvider implements WallpaperProvider {
    @Override
    public Wallpaper nextWallpaper() {
        Wallpaper wallpaper = new Wallpaper(MockWallpaperProvider.class.getResource("/sample.jpg"), null);
        wallpaper.setTitle(new URLUtils().titleFromURL(wallpaper.getURL()));
        new FileUtils().saveFromUrl(wallpaper.getURL(), wallpaper.getFilename());
        return wallpaper;
    }
}
