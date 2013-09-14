package ui;

import com.humbertopinheiro.application.Application;
import com.humbertopinheiro.ui.MainWindow;
import com.humbertopinheiro.wallpaper.EarthPornSite;
import com.humbertopinheiro.wallpaper.MockWallpaperProvider;
import com.humbertopinheiro.wallpaper.WallpaperProvider;
import org.fest.swing.edt.FailOnThreadViolationRepaintManager;
import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.fixture.FrameFixture;
import org.fest.swing.fixture.JComboBoxFixture;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.CoreMatchers.is;


/**
 * Created with IntelliJ IDEA.
 * User: humberto
 * Date: 05/09/13
 * Time: 22:56
 */
@RunWith(JUnit4.class)
public class MainWindowTest {

    public static final String WALLPAPER_PROVIDERS = "wallpaperProviders";
    private FrameFixture window;

    @BeforeClass
    public static void setUpOnce() {
        FailOnThreadViolationRepaintManager.install();
        WallpaperProvider[] providers = { new EarthPornSite(), new MockWallpaperProvider() };
        Application.INSTANCE.setWallpaperProviders(providers);
    }

    @Before
    public void setUp() {
        MainWindow mainWindow = GuiActionRunner.execute(new GuiQuery<MainWindow>() {
            protected MainWindow executeInEDT() {
                return new MainWindow();
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
        JComboBoxFixture jComboBoxFixture = window.comboBox(WALLPAPER_PROVIDERS);
        jComboBoxFixture.selectItem(1);
        Assert.assertThat(window.component().getTitle(), is("Blah"));
    }

}
