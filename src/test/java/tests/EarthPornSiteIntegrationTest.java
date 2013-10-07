package tests;

import static org.junit.Assert.*;

import com.humbertopinheiro.application.Application;
import com.humbertopinheiro.application.SystemProperties;
import com.humbertopinheiro.utils.ImageExtension;
import com.humbertopinheiro.wallpaper.EarthPornSite;
import com.humbertopinheiro.wallpaper.Wallpaper;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;


/**
 * Created with IntelliJ IDEA.
 * User: humberto
 * Date: 13/09/13
 * Time: 22:28
 */
@RunWith(MockitoJUnitRunner.class)
public class EarthPornSiteIntegrationTest {

    @BeforeClass
    public static void setUpClass() {
        Application.INSTANCE.setWallpaperStore(SystemProperties.INSTANCE.getTempDir());
    }

    @Test
    public void shouldDownloadImage() {
        EarthPornSite earthPornSite = new EarthPornSite();
        Wallpaper wallpaper = earthPornSite.nextWallpaper();
        assertTrue(new File(wallpaper.getFilename()).exists());
        assertTrue(new ImageExtension(wallpaper.getFilename()).isValid());
    }
}
