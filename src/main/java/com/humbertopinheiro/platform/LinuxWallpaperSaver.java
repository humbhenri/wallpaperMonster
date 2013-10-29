package com.humbertopinheiro.platform;

import static java.util.logging.Logger.getLogger;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import com.humbertopinheiro.application.SystemProperties;

public abstract class LinuxWallpaperSaver extends WallpaperSaver{

	public static WallpaperSaver getWallpaperSaver() {
		String desktop = System.getenv("DESKTOP_SESSION");
		if (desktop.indexOf("gnome") >= 0) {
			return new GnomeWallpaperSaver();
		} 
		return new NotImplementedWallpaperSaver();
	}
	
	@Override
	protected String getUserPathImagesFolder() {
		return SystemProperties.INSTANCE.getUserHome();
	}

}

class GnomeWallpaperSaver extends LinuxWallpaperSaver {
	
	private final static Logger LOGGER = getLogger(GnomeWallpaperSaver.class.getName());
	
	private final static String command = "gsettings set org.gnome.desktop.background picture-uri file:///%s";

	@Override
	protected void setWallpaperBackground(Path path) {
		try {
            String commandToSetBackground = String.format(command, path.toFile().getAbsolutePath());
			Runtime.getRuntime().exec(commandToSetBackground);
            LOGGER.info("wallpaper saved");
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
        }
	}
	
}
