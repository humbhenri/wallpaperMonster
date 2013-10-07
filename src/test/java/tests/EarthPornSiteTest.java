package tests;

import com.humbertopinheiro.utils.FileUtils;
import com.humbertopinheiro.utils.ImageExtension;
import com.humbertopinheiro.wallpaper.EarthPornSite;
import com.humbertopinheiro.wallpaper.URLDownloader;
import com.humbertopinheiro.wallpaper.Wallpaper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.InputStream;
import java.net.URL;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

/**
 * Created with IntelliJ IDEA.
 * User: humberto
 * Date: 13/09/13
 * Time: 22:08
 */
@RunWith(MockitoJUnitRunner.class)
public class EarthPornSiteTest {

    @Mock
    private URLDownloader urlDownloader;

    @Mock
    private FileUtils fileUtils;

    @InjectMocks
    private EarthPornSite earthPornSite = new EarthPornSite();

    @Before
    public void setUp() {
        when(urlDownloader.getHTML()).thenReturn(getHTMLContent());
        doNothing().when(fileUtils).saveFromUrl(any(URL.class), anyString());
    }

    @Test
    public void nextWallpaperURLMustBeAnImage() {
        String url = earthPornSite.nextWallpaper().getURL().toString();
        assertTrue(new ImageExtension(url).isValid());
    }

    @Test
    public void hasPreviousShouldReturnFalseIfCalledMoreTimesThanNext() {
        earthPornSite.nextWallpaper();
        earthPornSite.nextWallpaper();
        earthPornSite.nextWallpaper();
        earthPornSite.previousWallpaper();
        earthPornSite.previousWallpaper();
        earthPornSite.previousWallpaper();
        assertNull(earthPornSite.previousWallpaper());
        assertFalse(earthPornSite.hasPrevious());
    }

    @Test
    public void shouldNotHasPreviousIfHasOnlyOneWallpaper() {
        assertFalse(earthPornSite.hasPrevious());
    }

    @Test
    public void previousMustBeDifferentFromCurrentWallpaper() {
        earthPornSite.nextWallpaper();
        Wallpaper current = earthPornSite.nextWallpaper();
        assertFalse(current.equals(earthPornSite.previousWallpaper()));
    }

    private String getHTMLContent() {
        InputStream is = EarthPornSite.class.getResourceAsStream("/EarthPornHTML.txt");
        return FileUtils.slurpFile(is);
    }
}
