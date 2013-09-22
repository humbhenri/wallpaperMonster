package tests;

import com.humbertopinheiro.utils.URLUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.net.URL;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: humberto
 * Date: 13/09/13
 * Time: 23:30
 */
@RunWith(MockitoJUnitRunner.class)
public class URLUtilsTest {

    @Test
    public void titleMustBeLastComponentRemovingExtension() {
        URLUtils urlUtils = new URLUtils();
        URL url = urlUtils.fromString("file:/Users/humberto/Documents/wallpaperMonster/target/classes/sample.jpg");
        assertThat(urlUtils.titleFromURL(url), is("sample"));
    }
}
