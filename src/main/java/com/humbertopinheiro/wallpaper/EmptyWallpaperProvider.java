package com.humbertopinheiro.wallpaper;

import java.util.Iterator;

import com.humbertopinheiro.wallpaper.model.WallpaperItem;

public class EmptyWallpaperProvider extends WallpaperProvider {

	@Override
	protected Iterator<WallpaperItem> getLinkIterator() {
		return null;
	}

	@Override
	protected String getSite() {
		return null;
	}

	@Override
	public boolean hasNext() {
		return false;
	}

	@Override
	public boolean hasPrevious() {
		return false;
	}

	@Override
	protected String getImageLink(WallpaperItem wallpaperLink) {
		return null;
	}

	@Override
	protected String getTitle(WallpaperItem wallpaperLink) {
		return null;
	}

}
