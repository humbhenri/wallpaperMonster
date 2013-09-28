package tests;

import com.humbertopinheiro.application.Application;
import com.humbertopinheiro.utils.ImageExtension;
import com.humbertopinheiro.wallpaper.Wallbase;
import com.humbertopinheiro.wallpaper.Wallpaper;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;

import static junit.framework.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: humberto
 * Date: 24/09/13
 * Time: 22:20
 */
public class WallbaseIntegrationTest {

    @BeforeClass
    public static void setUpClass() {
        Application.INSTANCE.setWallpaperStore("/tmp");
    }

    @Test
    public void shouldDownloadImage() {
        Wallbase wallbase = new Wallbase();
        Wallpaper wallpaper = wallbase.nextWallpaper();
        assertTrue(new File(wallpaper.getFilename()).exists());
        assertTrue(new ImageExtension(wallpaper.getFilename()).isValid());
    }
}
