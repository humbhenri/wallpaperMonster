package com.humbertopinheiro.platform;

import com.humbertopinheiro.utils.FileUtils;
import com.humbertopinheiro.utils.URLUtils;
import com.humbertopinheiro.wallpaper.Wallpaper;

import java.io.File;
import java.nio.file.Path;

/**
 * Created with IntelliJ IDEA.
 * User: humberto
 * Date: 28/09/13
 * Time: 15:19
 */
public abstract class WallpaperSaver {
    private FileUtils fileUtils = new FileUtils();
    private URLUtils urlUtils = new URLUtils();

    public void saveAsBackground(Wallpaper wallpaper) {
        Path copy = fileUtils.copy(new File(wallpaper.getFilename()),
                new File(getUserPathImagesFolder(), urlUtils.lastComponent(wallpaper.getFilename())));
        if (copy != null) {
            setWallpaperBackground(copy);
        }
    }

    protected abstract String getUserPathImagesFolder();

    protected abstract void setWallpaperBackground(Path path);
}
