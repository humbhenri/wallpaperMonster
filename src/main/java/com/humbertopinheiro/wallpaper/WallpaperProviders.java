package com.humbertopinheiro.wallpaper;

public class WallpaperProviders {

	public WallpaperProvider[] getWallpaperProviders() {
		return new WallpaperProvider[] { 
			new EarthPornSite()
			, new RedditWallpapers()
			, new RedditMinimal()
		};
	}
}
