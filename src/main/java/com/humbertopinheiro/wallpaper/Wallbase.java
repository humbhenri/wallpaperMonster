package com.humbertopinheiro.wallpaper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.Iterator;

import com.google.common.base.Function;
import com.google.common.collect.Iterators;
import com.humbertopinheiro.wallpaper.model.WallpaperItem;

/**
 * Created with IntelliJ IDEA. User: humberto Date: 24/09/13 Time: 22:15
 */
public class Wallbase extends WallpaperProvider {
    private String title;

    @Override
    protected Iterator<WallpaperItem> getLinkIterator() {
        return Iterators.transform(Jsoup.parse(getUrlDownloader().getHTML())
                .select("div.wrapper > a[target]").iterator(),
                new Function<Element, WallpaperItem>(){

                    @Override
                    public WallpaperItem apply(Element arg0) {
                        return WallpaperItemFactory.fromElement(arg0);
                    }
                    
                });
    }

    @Override
    protected String getSite() {
        return "http://wallbase.cc/toplist";
    }

    @Override
    protected String getImageLink(WallpaperItem wallpaperLink) {
        Document clickThumbnail = Jsoup.parse(new URLDownloader(wallpaperLink.attr("href"))
                .getHTML());
        title = clickThumbnail.title();
        return clickThumbnail.select("img.wall").attr("src");
    }

    @Override
    protected String getTitle(WallpaperItem wallpaperLink) {
        return title;
    }

    @Override
    public String toString() {
        return "Wallbase";
    }

}
