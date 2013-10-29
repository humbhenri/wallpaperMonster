package com.humbertopinheiro.platform;

import com.humbertopinheiro.application.SystemProperties;

public class Platform {

	public WallpaperSaver getWallpaperSaver() {
		WallpaperSaver wallpaperSaver;
		final String OS = SystemProperties.INSTANCE.getOS().toLowerCase();
		if (OS.indexOf("mac") >= 0) {
			wallpaperSaver = new MacWallpaperSaver();
		} else if (OS.indexOf("win") >= 0) {
			wallpaperSaver = new WinWallpaperSaver();
		} else if(OS.indexOf("linux") >= 0) {
			wallpaperSaver = LinuxWallpaperSaver.getWallpaperSaver();
		} else {
			wallpaperSaver = new NotImplementedWallpaperSaver();
		}
		return wallpaperSaver;
	}
}
