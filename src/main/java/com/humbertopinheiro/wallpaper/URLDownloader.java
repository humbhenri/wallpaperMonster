package com.humbertopinheiro.wallpaper;

import com.humbertopinheiro.utils.URLUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
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

    public InputStream getInputStream() {
        try {
            HttpURLConnection con = (HttpURLConnection) new URLUtils().fromString(url).openConnection();
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);
            con.addRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:56.0) Gecko/20100101 Firefox/56.0");
            return con.getInputStream();
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
            return null;
        }
    }
}
