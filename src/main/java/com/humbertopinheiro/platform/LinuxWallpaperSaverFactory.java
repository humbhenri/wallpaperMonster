package com.humbertopinheiro.platform;

public class LinuxWallpaperSaverFactory extends WallpaperSaverFactory {

	@Override
	public WallpaperSaver getWallpaperSaver(String os) {
		if (os.indexOf("linux") > 0) {
			return new LinuxWallpaperSaver();
		}
		return super.getWallpaperSaver(os);
	}
}
