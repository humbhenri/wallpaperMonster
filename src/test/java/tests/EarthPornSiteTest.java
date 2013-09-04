package tests;

import com.humbertopinheiro.utils.FileUtils;
import com.humbertopinheiro.wallpaper.EarthPornSite;
import com.humbertopinheiro.utils.ImageExtension;
import com.humbertopinheiro.wallpaper.URLDownloader;
import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import static org.easymock.EasyMock.*;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;

/**
 * Created with IntelliJ IDEA.
 * User: humberto
 * Date: 03/09/13
 * Time: 20:59
 */
@RunWith(JUnit4.class)
public class EarthPornSiteTest {

    @Test
    public void shouldGetImagesLinks() {
        EarthPornSite earthPornSite = new EarthPornSite();
        Assert.assertThat(earthPornSite.nextImageLink(), instanceOf(URL.class));
    }

    @Test
    public void linkReturnedMustBeImages() {
        EarthPornSite earthPornSite = new EarthPornSite();
        Assert.assertTrue(new ImageExtension(earthPornSite.nextImageLink().toString()).isValid());
    }

    @Test
    public void shouldReturnValidLink() throws IOException {
        InputStream is = EarthPornSite.class.getResourceAsStream("/EarthPornHTML.txt");
        String frontPageExample = FileUtils.convertStreamToString(is);

        URLDownloader urlDownloader = EasyMock.createMock(URLDownloader.class);
        expect(urlDownloader.getHTML()).andReturn(frontPageExample);
        replay(urlDownloader);

        EarthPornSite earthPornSite = new EarthPornSite();
        earthPornSite.setUrlDownloader(urlDownloader);
        Assert.assertThat(earthPornSite.nextImageLink(), is(new URL("http://farm3.staticflickr.com/2039/2250675921_d4d3591ed8_o.jpg")));

        verify(urlDownloader);
    }
}
