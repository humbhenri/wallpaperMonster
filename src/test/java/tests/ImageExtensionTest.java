package tests;

import com.humbertopinheiro.utils.ImageExtension;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created with IntelliJ IDEA.
 * User: humberto
 * Date: 03/09/13
 * Time: 21:19
 */
@RunWith(JUnit4.class)
public class ImageExtensionTest {

    @Test
    public void shouldValidatePNG() {
        ImageExtension imageExtension = new ImageExtension("png");
        Assert.assertTrue(imageExtension.isValid());
    }

    @Test
    public void shouldValidateJPG() {
        String[] jpgs = {"jpg", "jpeg"};
        for (String ext : jpgs) {
            Assert.assertTrue(new ImageExtension(ext).isValid());
        }
    }

    @Test
    public void shouldBeCaseInsensitive() {
        Assert.assertTrue(new ImageExtension("PNG").isValid());
    }

    @Test
    public void shouldValidateAFilePath() {
        Assert.assertTrue(new ImageExtension("luciana vendramini.JPEG").isValid());
    }
}
