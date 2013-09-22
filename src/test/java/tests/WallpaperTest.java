package tests;

import com.humbertopinheiro.application.Application;
import com.humbertopinheiro.utils.URLUtils;
import com.humbertopinheiro.wallpaper.Wallpaper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.CoreMatchers.is;

/**
 * Created with IntelliJ IDEA.
 * User: humberto
 * Date: 05/09/13
 * Time: 22:40
 */
@RunWith(JUnit4.class)
public class WallpaperTest {

    @Before
    public void setUp() {
        Application.INSTANCE.setWallpaperStore("/tmp");
    }

    @Test
    public void shouldSaveFromURL() {
        URLUtils urlUtils = new URLUtils();
        Wallpaper wallpaper = new Wallpaper(urlUtils.fromString("http://www.site.com/fig.jpg"), null);
        Assert.assertThat(wallpaper.getFilename(), is("/tmp/fig.jpg"));
    }

}
