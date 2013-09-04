package com.humbertopinheiro.wallpaper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: humberto
 * Date: 03/09/13
 * Time: 21:00
 */
public class EarthPornSite {

    private URLDownloader urlDownloader;

    public URL nextImageLink() {
        if (urlDownloader == null) {
            urlDownloader = new URLDownloader("http://www.reddit.com/r/earthporn");
        }
        Document doc = Jsoup.parse(urlDownloader.getHTML());
        Elements links = doc.select("a.title");
        try {
            return new URL(links.get(0).data());
        } catch (MalformedURLException e) {
            return null;
        }
    }

    public void setUrlDownloader(URLDownloader urlDownloader) {
        this.urlDownloader = urlDownloader;
    }

    public URLDownloader getUrlDownloader() {
        return urlDownloader;
    }
}
