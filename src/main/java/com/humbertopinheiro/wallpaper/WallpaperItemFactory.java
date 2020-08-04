package com.humbertopinheiro.wallpaper;

import com.humbertopinheiro.wallpaper.model.WallpaperItem;
import com.humbertopinheiro.wallpaper.model.RedditJson.ChildData;

import org.jsoup.nodes.Element;

public class WallpaperItemFactory {

    public static WallpaperItem fromElement(final Element e) {
        return new WallpaperItemElement(e);
    }

    public static WallpaperItem fromReddit(ChildData arg0) {
        return new WallpaperItemChildData(arg0);
    }
}

class WallpaperItemElement implements WallpaperItem {

    private Element e;

    public WallpaperItemElement(Element e) {
        this.e = e;
    }

    @Override
    public String attr(final String attributeKey) {
        return e.attr(attributeKey);
    }

    @Override
    public String text() {
        return e.text();
    }

};

class WallpaperItemChildData implements WallpaperItem {

    private ChildData childData;

    public WallpaperItemChildData(ChildData arg0) {
        this.childData = arg0;
    }

    @Override
    public String attr(String string) {
        return this.childData.getUrl();
    }

    @Override
    public String text() {
        return this.childData.getTitle();
    }

}