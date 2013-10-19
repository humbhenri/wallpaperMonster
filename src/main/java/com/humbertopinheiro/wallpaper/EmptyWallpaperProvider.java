package com.humbertopinheiro.wallpaper;

import java.util.Iterator;

import org.jsoup.nodes.Element;

public class EmptyWallpaperProvider extends WallpaperProvider {

	@Override
	protected Iterator<Element> getLinkIterator() {
		return null;
	}

	@Override
	protected String getSite() {
		return null;
	}

	@Override
	protected String getImageLink(Element wallpaperLink) {
		return null;
	}

	@Override
	protected String getTitle(Element wallpaperLink) {
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

}
