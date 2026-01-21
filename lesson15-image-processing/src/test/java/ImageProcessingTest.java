import java.awt.image.BufferedImage;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for image processing operations
 */
public class ImageProcessingTest {

    @Test
    public void testCreateSampleImage() {
        BufferedImage image = ImageProcessingUI.createSampleImage(100, 100);
        assertNotNull("Image should not be null", image);
        assertEquals("Width should be 100", 100, image.getWidth());
        assertEquals("Height should be 100", 100, image.getHeight());
    }

    @Test
    public void testApplyGrayscale() {
        BufferedImage image = ImageProcessingUI.createSampleImage(50, 50);
        BufferedImage grayscale = ImageProcessingUI.applyGrayscale(image);

        assertNotNull("Grayscale image should not be null", grayscale);
        assertEquals("Dimensions should match", image.getWidth(), grayscale.getWidth());
        assertEquals("Dimensions should match", image.getHeight(), grayscale.getHeight());

        // Check that resulting pixels have equal RGB components (gray)
        for (int y = 0; y < grayscale.getHeight(); y++) {
            for (int x = 0; x < grayscale.getWidth(); x++) {
                int rgb = grayscale.getRGB(x, y);
                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = rgb & 0xFF;
                assertEquals("R should equal G", r, g);
                assertEquals("G should equal B", g, b);
            }
        }
    }

    @Test
    public void testAdjustBrightness() {
        BufferedImage image = ImageProcessingUI.createSampleImage(50, 50);
        BufferedImage brightened = ImageProcessingUI.adjustBrightness(image, 1.5f);

        assertNotNull("Brightened image should not be null", brightened);
        assertEquals("Dimensions should match", image.getWidth(), brightened.getWidth());

        // Brightened image should have higher average brightness
        ImageProcessingUI.ImageStats originalStats = ImageProcessingUI.getImageStatistics(image);
        ImageProcessingUI.ImageStats brightenedStats = ImageProcessingUI.getImageStatistics(brightened);
        assertTrue("Brightened image should have higher brightness",
                brightenedStats.avgBrightness > originalStats.avgBrightness);
    }

    @Test
    public void testApplySimpleBlur() {
        BufferedImage image = ImageProcessingUI.createSampleImage(50, 50);
        BufferedImage blurred = ImageProcessingUI.applySimpleBlur(image);

        assertNotNull("Blurred image should not be null", blurred);
        assertEquals("Dimensions should match", image.getWidth(), blurred.getWidth());
    }

    @Test
    public void testApplyEdgeDetection() {
        BufferedImage image = ImageProcessingUI.createSampleImage(50, 50);
        BufferedImage edges = ImageProcessingUI.applyEdgeDetection(image);

        assertNotNull("Edge-detected image should not be null", edges);
        assertEquals("Dimensions should match", image.getWidth(), edges.getWidth());
    }

    @Test
    public void testGetImageStatistics() {
        BufferedImage image = ImageProcessingUI.createSampleImage(50, 50);
        ImageProcessingUI.ImageStats stats = ImageProcessingUI.getImageStatistics(image);

        assertNotNull("Stats should not be null", stats);
        assertEquals("Width should be 50", 50, stats.width);
        assertEquals("Height should be 50", 50, stats.height);
        assertTrue("Average red should be in valid range", stats.avgRed >= 0 && stats.avgRed <= 255);
        assertTrue("Average green should be in valid range", stats.avgGreen >= 0 && stats.avgGreen <= 255);
        assertTrue("Average blue should be in valid range", stats.avgBlue >= 0 && stats.avgBlue <= 255);
        assertTrue("Average brightness should be in valid range",
                stats.avgBrightness >= 0 && stats.avgBrightness <= 255);
    }
}
