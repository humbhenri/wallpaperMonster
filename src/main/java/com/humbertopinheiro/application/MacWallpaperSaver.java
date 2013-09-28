package com.humbertopinheiro.application;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import static java.lang.Runtime.getRuntime;
import static java.lang.System.getProperty;
import static java.util.logging.Logger.getLogger;

/**
 * Created with IntelliJ IDEA.
 * User: humberto
 * Date: 28/09/13
 * Time: 15:47
 */
public class MacWallpaperSaver extends WallpaperSaver {
    private final static Logger LOGGER = getLogger(MacWallpaperSaver.class.getName());

    @Override
    protected String getUserPathImagesFolder() {
        return new File(getProperty("user.home"), "Pictures").getAbsolutePath();
    }

    @Override
    protected void setWallpaperBackground(Path path) {
        String as[] = {
                "osascript",
                "-e", "tell application \"Finder\"",
                "-e", "set desktop picture to POSIX file \"" + path.toAbsolutePath().toString() + "\"",
                "-e", "end tell"
        };
        try {
            getRuntime().exec(as);
            LOGGER.info("wallpaper saved");
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
        }
    }
}
