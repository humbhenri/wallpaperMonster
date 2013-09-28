package com.humbertopinheiro.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Logger;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/**
 * Created with IntelliJ IDEA.
 * User: humberto
 * Date: 03/09/13
 * Time: 22:09
 */
public class FileUtils {

    private final static Logger LOGGER = Logger.getLogger(FileUtils.class .getName());

    public static String slurpFile(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    public void saveFromUrl(URL url, String filename) {
        try {
            ReadableByteChannel rbc = Channels.newChannel(url.openStream());
            FileOutputStream fos = new FileOutputStream(filename);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            LOGGER.info(url + " saved to " + filename);
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    public Path copy(File src, File dst) {
        try {
            return Files.copy(src.toPath(), dst.toPath(), REPLACE_EXISTING);
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
        }
        return null;
    }

}
