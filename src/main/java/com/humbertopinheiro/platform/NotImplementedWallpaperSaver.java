package com.humbertopinheiro.platform;

import static java.util.logging.Logger.getLogger;

import java.nio.file.Path;
import java.util.logging.Logger;

public class NotImplementedWallpaperSaver extends WallpaperSaver {
	private final static Logger LOGGER = getLogger(NotImplementedWallpaperSaver.class
			.getName());

	@Override
	protected String getUserPathImagesFolder() {
		return null;
	}

	@Override
	protected void setWallpaperBackground(Path path) {
		LOGGER.info("Not implemented for this operating system.");
	}
}
