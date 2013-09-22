package com.humbertopinheiro.wallpaper;

import com.humbertopinheiro.utils.URLUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: humberto
 * Date: 03/09/13
 * Time: 21:46
 */
public class URLDownloader {
    private final String url;
    private final static Logger LOGGER = Logger.getLogger(URLDownloader.class.getName());

    public URLDownloader(String url) {
        this.url = url;
    }

    public String getHTML() {
        StringBuilder output = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new URLUtils().fromString(url).openStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line);
            }
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
        }
        return output.toString();
    }
}
