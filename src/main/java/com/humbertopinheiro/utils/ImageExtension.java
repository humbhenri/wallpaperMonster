package com.humbertopinheiro.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * Created with IntelliJ IDEA.
 * User: humberto
 * Date: 03/09/13
 * Time: 21:21
 */
public class ImageExtension {
    private final String extension;

    public ImageExtension(String ext) {
        this.extension = ext;
    }

    public boolean isValid() {
        return StringUtils.endsWithIgnoreCase(extension, "png") || StringUtils.endsWithIgnoreCase(extension, "jpg")
                || StringUtils.endsWithIgnoreCase(extension, "jpeg");
    }
}
