package com.humbertopinheiro.wallpaper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.logging.Logger;

import com.google.common.base.Function;
import com.google.common.collect.Iterators;
import com.humbertopinheiro.wallpaper.model.RedditJson;
import com.humbertopinheiro.wallpaper.model.RedditJson.ChildData;
import com.humbertopinheiro.wallpaper.model.WallpaperItem;

/**
 * Created with IntelliJ IDEA. User: humberto Date: 03/09/13 Time: 21:00
 */
public class EarthPornSite extends WallpaperProvider {

    private static final Logger LOGGER = Logger.getLogger(EarthPornSite.class.getName());

    @Override
    protected Iterator<WallpaperItem> getLinkIterator() {
        try {
            final InputStream inputStream = getUrlDownloader().getInputStream();
            final RedditJson jsonVal = getObjectMapper().readerFor(RedditJson.class).readValue(inputStream);
            inputStream.close();
            return Iterators.transform(jsonVal.getChildrenData().iterator(), new Function<ChildData, WallpaperItem>() {

				@Override
				public WallpaperItem apply(final ChildData arg0) {
					return WallpaperItemFactory.fromReddit(arg0);
				}
                
            });

        } catch (final IOException e) {
            LOGGER.severe(e.getMessage());
            return Iterators.emptyIterator();
        }
    }

    @Override
    protected String getSite() {
        return "https://www.reddit.com/r/EarthPorn/top.json";
    }

    @Override
    public String toString() {
        return "EarthPorn";
    }

    @Override
    protected String getImageLink(final WallpaperItem wallpaperLink) {
        return wallpaperLink.attr("href");
    }

    @Override
    protected String getTitle(final WallpaperItem wallpaperLink) {
        return wallpaperLink.text();
    }
}
