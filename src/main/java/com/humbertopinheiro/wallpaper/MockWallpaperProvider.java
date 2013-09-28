package com.humbertopinheiro.wallpaper;

import com.humbertopinheiro.utils.FileUtils;
import com.humbertopinheiro.utils.URLUtils;
import org.jsoup.nodes.Element;

import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: humberto
 * Date: 12/09/13
 * Time: 22:35
 */
public class MockWallpaperProvider extends WallpaperProvider {
    @Override
    protected Iterator<Element> getLinkIterator() {
        return null;
    }

    @Override
    protected String getSite() {
        return null;
    }

    @Override
    public Wallpaper nextWallpaper() {
        Wallpaper wallpaper = new Wallpaper(MockWallpaperProvider.class.getResource("/sample.jpg"), null);
        wallpaper.setTitle(new URLUtils().titleFromURL(wallpaper.getURL()));
        new FileUtils().saveFromUrl(wallpaper.getURL(), wallpaper.getFilename());
        return wallpaper;
    }

    @Override
    public Wallpaper previousWallpaper() {
        return null;
    }

    @Override
    public boolean hasPrevious() {
        return false;
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    protected String getImageLink(Element wallpaperLink) {
        return null;
    }

    @Override
    protected String getTitle(Element wallpaperLink) {
        return "sample";
    }

    @Override
    public String toString() {
        return "Sample";
    }
}
