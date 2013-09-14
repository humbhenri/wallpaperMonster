package com.humbertopinheiro.utils;

import org.apache.commons.lang3.StringUtils;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: humberto
 * Date: 12/09/13
 * Time: 23:20
 */
public class URLUtils {
    public URL fromString(String url) {
        try {
            return new URL(url);
        } catch (MalformedURLException e) {
            // TODO logging
            return null;
        }
    }

    public String titleFromURL(URL url) {
        // TODO
        return null;
    }

    public String lastComponent(URL url) {
        String[] split = StringUtils.split(url.toString(), "/");
        return split[split.length - 1];
    }
}
