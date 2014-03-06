package com.humbertopinheiro.platform;

import static java.util.logging.Logger.getLogger;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class LinuxWallpaperSaver implements WallpaperSaver {

	private final static String command = "gsettings set org.gnome.desktop.background picture-uri file:///%s";

	private final static Logger LOGGER = getLogger(LinuxWallpaperSaver.class
			.getName());

	@Override
	public void setWallpaperBackground(File file) {
		try {
			String commandToSetBackground = String.format(command,
					file.getAbsolutePath());
			Runtime.getRuntime().exec(commandToSetBackground);
			LOGGER.info("wallpaper saved");
		} catch (IOException e) {
			LOGGER.severe(e.getMessage());
		}
	}

}
