package com.humbertopinheiro.wallpaper;

/**
 * Created with IntelliJ IDEA.
 * User: humberto
 * Date: 03/09/13
 * Time: 20:43
 */

import static com.google.common.collect.Lists.newArrayList;

import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.humbertopinheiro.utils.FileUtils;
import com.humbertopinheiro.utils.ImageExtension;
import com.humbertopinheiro.utils.URLUtils;
import com.humbertopinheiro.wallpaper.model.WallpaperItem;

public abstract class WallpaperProvider {

    private URLDownloader urlDownloader;
    private Iterator<WallpaperItem> linkIterator;
    private FileUtils fileUtils = new FileUtils();
    private List<Wallpaper> wallpapers = newArrayList(new Wallpaper(""));
    private int currentWallpaper = 0;
    private final ObjectMapper objectMapper;

    public WallpaperProvider() {
        objectMapper = new ObjectMapper();
        urlDownloader = new URLDownloader(getSite());
        linkIterator = getLinkIterator();
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    protected abstract Iterator<WallpaperItem> getLinkIterator();

    protected abstract String getSite();

    protected abstract String getImageLink(WallpaperItem wallpaperLink);

    protected abstract String getTitle(WallpaperItem wallpaperLink);

    public Wallpaper nextWallpaper() {
        if (hasNext()) {
            if (currentWallpaper == wallpapers.size() - 1) {
                wallpapers.add(downloadWallpaper());
            }
            return wallpapers.get(++currentWallpaper);
        }
        return null;
    }

    public Wallpaper previousWallpaper() {
        if (hasPrevious()) {
            return wallpapers.get(--currentWallpaper);
        }
        return null;
    }

    public boolean hasPrevious() {
        return currentWallpaper > 1;
    }

    public boolean hasNext() {
        return linkIterator.hasNext();
    }

    private Wallpaper downloadWallpaper() {
        if (hasNext()) {
            WallpaperItem wallpaperLink = linkIterator.next();
            String title = getTitle(wallpaperLink);
            String imageLink = getImageLink(wallpaperLink);
            if (new ImageExtension(imageLink).isValid()) {
                Wallpaper wallpaper = new Wallpaper(new URLUtils().fromString(imageLink), title);
                fileUtils.saveFromUrl(wallpaper.getURL(), wallpaper.getFilename());
                return wallpaper;
            }
            return downloadWallpaper();
        }
        return null;
    }

    public URLDownloader getUrlDownloader() {
        return urlDownloader;
    }
}

