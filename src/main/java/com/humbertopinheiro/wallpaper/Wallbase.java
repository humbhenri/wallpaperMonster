package com.humbertopinheiro.wallpaper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: humberto
 * Date: 24/09/13
 * Time: 22:15
 */
public class Wallbase extends WallpaperProvider {
    private String title;

    @Override
    protected Iterator<Element> getLinkIterator() {
        return Jsoup.parse(getUrlDownloader().getHTML())
                .select("div.wrapper > a[target]").iterator();
    }

    @Override
    protected String getSite() {
        return "http://wallbase.cc/toplist";
    }

    @Override
    protected String getImageLink(Element wallpaperLink) {
        Document clickThumbnail = Jsoup.parse(new URLDownloader(wallpaperLink.attr("href"))
                .getHTML());
        title = clickThumbnail.title();
        return clickThumbnail.select("img.wall").attr("src");
    }

    @Override
    protected String getTitle(Element wallpaperLink) {
        return title;
    }

    @Override
    public String toString() {
        return "Wallbase";
    }

}
