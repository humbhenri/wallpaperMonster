package ui;

import static org.hamcrest.CoreMatchers.is;

import org.fest.swing.edt.FailOnThreadViolationRepaintManager;
import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.fixture.FrameFixture;
import org.fest.swing.fixture.JComboBoxFixture;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.humbertopinheiro.ui.MainWindow;
import com.humbertopinheiro.ui.WallpaperPanel;
import com.humbertopinheiro.wallpaper.EmptyWallpaperProvider;

/**
 * Created with IntelliJ IDEA. User: humberto Date: 05/09/13 Time: 22:56
 */
@RunWith(MockitoJUnitRunner.class)
public class MainWindowTest {

	public static final String WALLPAPER_PROVIDERS = "wallpaperProviders";
	private FrameFixture window;

	@BeforeClass
	public static void setUpOnce() {
		FailOnThreadViolationRepaintManager.install();
	}

	@Before
	public void setUp() {
		MainWindow mainWindow = GuiActionRunner
				.execute(new GuiQuery<MainWindow>() {
					@Override
					protected MainWindow executeInEDT() {
						return new MainWindow(new WallpaperPanel(
								new EmptyWallpaperProvider()));
					}
				});
		window = new FrameFixture(mainWindow);
		window.show(); // shows the frame to test
	}

	@After
	public void tearDown() {
		window.cleanUp();
	}

	@Test
	public void shouldBeVisible() {
		window.requireVisible();
	}

	@Test
	public void shouldDisplayWallpapersProviderList() {
		window.label("info").requireText("Choose wallpaper provider");
		window.comboBox(WALLPAPER_PROVIDERS).requireVisible();
	}

	@Test
	public void shouldDisplayWallpaperTitleWhenClickAProvider() {
		JComboBoxFixture jComboBoxFixture = window
				.comboBox(WALLPAPER_PROVIDERS);
		jComboBoxFixture.selectItem(1);
		Assert.assertThat(window.component().getTitle(), is("sample"));
	}

}
