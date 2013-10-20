package com.humbertopinheiro.wallpaper;

import java.io.File;
import java.net.URL;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.humbertopinheiro.application.WallpaperStore;
import com.humbertopinheiro.utils.URLUtils;

/**
 * Created with IntelliJ IDEA. User: humberto Date: 03/09/13 Time: 20:50
 */
public class Wallpaper {

	private URL url;
	private String title;
	private String filename;
	private final WallpaperStore wallpaperStore = new WallpaperStore();

	public Wallpaper(String title) {
		this.title = title;
	}

	public Wallpaper(URL url, String title) {
		this.title = title;
		URLUtils urlUtils = new URLUtils();
		this.filename = new File(wallpaperStore.getWallpaperStore(),
				urlUtils.lastComponent(url)).getAbsolutePath();
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public String getFilename() {
		return filename;
	}

	public URL getURL() {
		return url;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj.getClass() != getClass()) {
			return false;
		}
		Wallpaper other = (Wallpaper) obj;
		return new EqualsBuilder().append(url, other.url).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(url).hashCode();
	}
}
