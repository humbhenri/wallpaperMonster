package tests;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.humbertopinheiro.utils.ImageExtension;
import com.humbertopinheiro.wallpaper.EarthPornSite;
import com.humbertopinheiro.wallpaper.Wallpaper;

/**
 * Created with IntelliJ IDEA. User: humberto Date: 13/09/13 Time: 22:28
 */
@RunWith(MockitoJUnitRunner.class)
public class EarthPornSiteIntegrationTest {

	@Test
	public void shouldDownloadImage() {
		EarthPornSite earthPornSite = new EarthPornSite();
		Wallpaper wallpaper = earthPornSite.nextWallpaper();
		assertTrue(new File(wallpaper.getFilename()).exists());
		assertTrue(new ImageExtension(wallpaper.getFilename()).isValid());
	}
}
