package tests;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import com.humbertopinheiro.utils.ImageExtension;
import com.humbertopinheiro.wallpaper.Wallbase;
import com.humbertopinheiro.wallpaper.Wallpaper;

/**
 * Created with IntelliJ IDEA. User: humberto Date: 24/09/13 Time: 22:20
 */
public class WallbaseIntegrationTest {

	@Test
	public void shouldDownloadImage() {
		Wallbase wallbase = new Wallbase();
		Wallpaper wallpaper = wallbase.nextWallpaper();
		assertTrue(new File(wallpaper.getFilename()).exists());
		assertTrue(new ImageExtension(wallpaper.getFilename()).isValid());
	}
}
