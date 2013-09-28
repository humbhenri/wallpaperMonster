package com.humbertopinheiro.wallpaper;

/**
 * Created with IntelliJ IDEA.
 * User: humberto
 * Date: 03/09/13
 * Time: 20:43
 */

import com.humbertopinheiro.utils.FileUtils;
import com.humbertopinheiro.utils.ImageExtension;
import com.humbertopinheiro.utils.URLUtils;
import org.jsoup.nodes.Element;

import java.util.Iterator;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public abstract class WallpaperProvider {

    private URLDownloader urlDownloader;
    private Iterator<Element> linkIterator;
    private FileUtils fileUtils = new FileUtils();
    private List<Wallpaper> wallpapers = newArrayList(null);
    private int currentWallpaper = 0;

    public WallpaperProvider() {
        urlDownloader = new URLDownloader(getSite());
        linkIterator = getLinkIterator();
    }

    protected abstract Iterator<Element> getLinkIterator();

    protected abstract String getSite();

    protected abstract String getImageLink(Element wallpaperLink);

    protected abstract String getTitle(Element wallpaperLink);

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
            Element wallpaperLink = linkIterator.next();
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

