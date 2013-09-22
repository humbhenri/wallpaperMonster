package com.humbertopinheiro.wallpaper;

import com.humbertopinheiro.application.Application;
import com.humbertopinheiro.utils.URLUtils;

import java.io.File;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: humberto
 * Date: 03/09/13
 * Time: 20:50
 */
public class Wallpaper {

    private URL url;
    private String title;
    private String filename;

    public Wallpaper(String title) {
        this.title = title;
    }

    public Wallpaper(URL url, String title) {
        this.title = title;
        URLUtils urlUtils = new URLUtils();
        this.filename = new File(Application.INSTANCE.getWallpaperStore(),
                urlUtils.lastComponent(url)).getAbsolutePath();
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public String getFilename() {
        return filename;
    }

    public URL getURL() {
        return url;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
