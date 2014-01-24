package com.humbertopinheiro.platform;

import static java.lang.Runtime.getRuntime;
import static java.util.logging.Logger.getLogger;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA. User: humberto Date: 28/09/13 Time: 15:47
 */
public class MacWallpaperSaver implements WallpaperSaver {
	private final static Logger LOGGER = getLogger(MacWallpaperSaver.class
			.getName());

	@Override
	public void setWallpaperBackground(File file) {
		String as[] = {
				"osascript",
				"-e",
				"tell application \"Finder\"",
				"-e",
				"set desktop picture to POSIX file \""
						+ file.getAbsolutePath().toString() + "\"", "-e",
				"end tell" };
		try {
			getRuntime().exec(as);
			LOGGER.info("wallpaper saved");
		} catch (IOException e) {
			LOGGER.severe(e.getMessage());
		}
	}
}
