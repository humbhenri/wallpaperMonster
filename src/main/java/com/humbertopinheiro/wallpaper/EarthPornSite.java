package com.humbertopinheiro.wallpaper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: humberto
 * Date: 03/09/13
 * Time: 21:00
 */
public class EarthPornSite extends WallpaperProvider {

    @Override
    protected Iterator<Element> getLinkIterator() {
        return Jsoup.parse(getUrlDownloader().getHTML())
                .select("a.title").iterator();
    }

    @Override
    protected String getSite() {
        return "http://www.reddit.com/r/earthporn";
    }

    @Override
    public String toString() {
        return "EarthPorn";
    }

    @Override
    protected String getImageLink(Element wallpaperLink) {
        return wallpaperLink.attr("href");
    }

    @Override
    protected String getTitle(Element wallpaperLink) {
        return wallpaperLink.text();
    }
}
