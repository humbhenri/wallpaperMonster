package com.humbertopinheiro.wallpaper;

import com.humbertopinheiro.utils.FileUtils;

import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: humberto
 * Date: 22/09/13
 * Time: 00:05
 */
public class LoadingWallpaper extends Wallpaper{

    private static URL GIF_URL = LoadingWallpaper.class.getResource("/loading.gif");

    public LoadingWallpaper() {
        super(GIF_URL, "loading ...");
        FileUtils fileUtils = new FileUtils();
        fileUtils.saveFromUrl(GIF_URL, super.getFilename());
    }
}
