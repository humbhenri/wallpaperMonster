package tests;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.File;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.humbertopinheiro.application.SystemProperties;
import com.humbertopinheiro.utils.URLUtils;
import com.humbertopinheiro.wallpaper.Wallpaper;

/**
 * Created with IntelliJ IDEA. User: humberto Date: 05/09/13 Time: 22:40
 */
@RunWith(JUnit4.class)
public class WallpaperTest {

	private static final String TEMP_DIR = SystemProperties.INSTANCE
			.getTempDir();

	@Test
	public void shouldSaveFromURL() {
		URLUtils urlUtils = new URLUtils();
		Wallpaper wallpaper = new Wallpaper(
				urlUtils.fromString("http://www.site.com/fig.jpg"), null);
		assertThat(wallpaper.getFilename(), is(path_join(TEMP_DIR, "fig.jpg")));
	}

	private String path_join(String dir, String file) {
		return new File(dir, file).getAbsolutePath();
	}

}
