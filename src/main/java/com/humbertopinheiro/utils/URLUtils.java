package com.humbertopinheiro.utils;

import static java.io.File.pathSeparator;
import static org.apache.commons.lang3.StringUtils.split;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: humberto
 * Date: 12/09/13
 * Time: 23:20
 */
public class URLUtils {

    private final static Logger LOGGER = Logger.getLogger(URLUtils.class .getName());

    public URL fromString(String url) {
        try {
            return new URL(url);
        } catch (MalformedURLException e) {
            LOGGER.severe(e.getMessage());
            return null;
        }
    }

    public String titleFromURL(URL url) {
        return StringUtils.split(lastComponent(url), ".")[0];
    }

    public String lastComponent(URL url) {
        String[] split = split(url.toString(), "/");
        return split[split.length - 1];
    }

    public String lastComponent(String url) {
        String[] split = StringUtils.split(url, "\\/");
        return split[split.length - 1];
    }
}
