package com.humbertopinheiro.wallpaper;

import com.google.common.collect.Lists;
import com.humbertopinheiro.utils.FileUtils;
import com.humbertopinheiro.utils.URLUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: humberto
 * Date: 03/09/13
 * Time: 21:00
 */
public class EarthPornSite implements WallpaperProvider {

    private URLDownloader urlDownloader;
    private Iterator<Element> linkIterator;
    private FileUtils fileUtils = new FileUtils();
    private final static Logger LOGGER = Logger.getLogger(EarthPornSite.class.getName());
    private List<Wallpaper> wallpapers = Lists.newArrayList(null);
    private int currentWallpaper = 0;

    public EarthPornSite() {
        urlDownloader = new URLDownloader("http://www.reddit.com/r/earthporn");
        Document doc = Jsoup.parse(urlDownloader.getHTML());
        Elements links = doc.select("a.title");
        linkIterator = links.iterator();
    }

    @Override
    public String toString() {
        return "www.reddit.com/r/earthporn";
    }

    public FileUtils getFileUtils() {
        return fileUtils;
    }

    public void setFileUtils(FileUtils fileUtils) {
        this.fileUtils = fileUtils;
    }

    public void setUrlDownloader(URLDownloader urlDownloader) {
        this.urlDownloader = urlDownloader;
    }

    public URLDownloader getUrlDownloader() {
        return urlDownloader;
    }

    public Wallpaper nextWallpaper() {
        if (hasNext()) {
            if (currentWallpaper == wallpapers.size() - 1) {
                wallpapers.add(downloadWallpaper());
            }
            return wallpapers.get(++currentWallpaper);
        }
        return null;
    }

    @Override
    public Wallpaper previousWallpaper() {
        if (hasPrevious()) {
            return wallpapers.get(--currentWallpaper);
        }
        return null;
    }

    @Override
    public boolean hasPrevious() {
        return currentWallpaper > 1;
    }

    @Override
    public boolean hasNext() {
        return linkIterator.hasNext();
    }

    private Wallpaper downloadWallpaper() {
        Element wallpaperLink = linkIterator.next();
        String title = wallpaperLink.text();
        Wallpaper wallpaper = new Wallpaper(new URLUtils().fromString(wallpaperLink.attr("href")), title);
        fileUtils.saveFromUrl(wallpaper.getURL(), wallpaper.getFilename());
        return wallpaper;
    }
}
