package com.humbertopinheiro.platform;

public class WallpaperSaverFactory {
	public WallpaperSaver getWallpaperSaver(String os) {
		WallpaperSaver wallpaperSaver;

		if (os.indexOf("mac") >= 0) {
			wallpaperSaver = new MacWallpaperSaver();
		} else if (os.indexOf("win") >= 0) {
			wallpaperSaver = new WinWallpaperSaver();
		} else if (os.indexOf("linux") >= 0) {
			wallpaperSaver = new LinuxWallpaperSaver();
		} else {
			wallpaperSaver = new NotImplementedWallpaperSaver();
		}
		return wallpaperSaver;
	}
}
