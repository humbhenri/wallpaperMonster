package com.humbertopinheiro.wallpaper;

public class RedditMinimal extends EarthPornSite {
    
    @Override
    protected String getSite() {
        return "https://www.reddit.com/r/MinimalWallpaper/top.json";
    }

    @Override
    public String toString() {
        return "MinimalWallpaper";
    }
}
