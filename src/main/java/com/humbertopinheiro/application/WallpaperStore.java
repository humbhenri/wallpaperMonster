package com.humbertopinheiro.application;

public class WallpaperStore {

	public String getWallpaperStore() {
		return SystemProperties.INSTANCE.getTempDir();
	}
}
