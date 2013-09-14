package com.humbertopinheiro.wallpaper;

import com.humbertopinheiro.utils.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: humberto
 * Date: 03/09/13
 * Time: 21:00
 */
public class EarthPornSite implements WallpaperProvider{

    private URLDownloader urlDownloader;
    private Iterator<Element> iterator;
    private FileUtils fileUtils;

    public EarthPornSite() {
        fileUtils = new FileUtils();
    }

    @Override
    public Wallpaper nextWallpaper() {
        Wallpaper wallpaper = new Wallpaper(nextImageLink());
        fileUtils.saveFromUrl(wallpaper.getURL(), wallpaper.getFilename());
        return wallpaper;
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

    private URL nextImageLink() {
        URL url = null;
        if (urlDownloader == null) {
            urlDownloader = new URLDownloader("http://www.reddit.com/r/earthporn");
        }
        if (iterator == null) {
            Document doc = Jsoup.parse(urlDownloader.getHTML());
            Elements links = doc.select("a.title");
            iterator = links.iterator();
        }
        if (iterator.hasNext()) {
            try {
                url = new URL(iterator.next().attr("href"));
            } catch (MalformedURLException e) {
                // TODO logging
            }
        }
        return url;
    }
}
