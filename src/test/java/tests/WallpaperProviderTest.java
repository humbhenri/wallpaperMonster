package tests;

import com.humbertopinheiro.wallpaper.WallpaperProvider;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created with IntelliJ IDEA.
 * User: humberto
 * Date: 03/09/13
 * Time: 20:35
 */
@RunWith(JUnit4.class)
public class WallpaperProviderTest {

    @Test
    public void mustProvideWallpapers() {
        WallpaperProvider wallpaperProvider = new WallpaperProvider();
        Assert.assertNotNull(wallpaperProvider.nextWallpaper());
    }
}
