package com.humbertopinheiro.platform;

import static java.util.logging.Logger.getLogger;

import java.io.File;
import java.util.logging.Logger;

public class NotImplementedWallpaperSaver implements WallpaperSaver {
	private final static Logger LOGGER = getLogger(NotImplementedWallpaperSaver.class
			.getName());

	@Override
	public void setWallpaperBackground(File file) {
		LOGGER.info("Not implemented for this operating system.");
	}
}
