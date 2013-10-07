package com.humbertopinheiro.utils;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: humberto
 * Date: 03/09/13
 * Time: 22:09
 */
public class FileUtils {

    private final static Logger LOGGER = Logger.getLogger(FileUtils.class .getName());

    public static String slurpFile(java.io.InputStream is) {
        try(Scanner scanner = new Scanner(is)) {
        	scanner.useDelimiter("\\A");
        	return scanner.hasNext() ? scanner.next() : "";
        }
    }

    public void saveFromUrl(URL url, String filename) {
        try (FileOutputStream fos = new FileOutputStream(filename)) {
        	ReadableByteChannel rbc = Channels.newChannel(url.openStream());
        	fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        	LOGGER.info(url + " saved to " + filename);
        } catch (Exception e) {
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
