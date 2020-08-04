package com.humbertopinheiro.wallpaper;

import static com.google.common.io.Resources.getResource;

import java.util.Iterator;

import org.jsoup.nodes.Element;

import com.humbertopinheiro.utils.FileUtils;
import com.humbertopinheiro.utils.URLUtils;
import com.humbertopinheiro.wallpaper.model.WallpaperItem;

/**
 * Created with IntelliJ IDEA. User: humberto Date: 12/09/13 Time: 22:35
 */
public class MockWallpaperProvider extends WallpaperProvider {

	private final FileUtils fileUtils = new FileUtils();

	@Override
	protected Iterator<WallpaperItem> getLinkIterator() {
		return null;
	}

	@Override
	protected String getSite() {
		return null;
	}

	@Override
	public Wallpaper nextWallpaper() {
		Wallpaper wallpaper = new Wallpaper(getResource("/sample.jpg"), null);
		wallpaper.setTitle(new URLUtils().titleFromURL(wallpaper.getURL()));
		fileUtils.saveFromUrl(wallpaper.getURL(), wallpaper.getFilename());
		return wallpaper;
	}

	@Override
	public Wallpaper previousWallpaper() {
		return null;
	}

	@Override
	public boolean hasPrevious() {
		return false;
	}

	@Override
	public boolean hasNext() {
		return true;
	}

	@Override
	protected String getImageLink(WallpaperItem wallpaperLink) {
		return null;
	}

	@Override
	protected String getTitle(WallpaperItem wallpaperLink) {
		return "sample";
	}

	@Override
	public String toString() {
		return "Sample";
	}
}
