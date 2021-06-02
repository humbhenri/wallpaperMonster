package com.humbertopinheiro.wallpaper;

public class RedditWallpapers extends EarthPornSite {
    @Override
    protected String getSite() {
        return "https://www.reddit.com/r/wallpapers/top.json";
    }

    @Override
    public String toString() {
        return "Wallpapers";
    }
}
