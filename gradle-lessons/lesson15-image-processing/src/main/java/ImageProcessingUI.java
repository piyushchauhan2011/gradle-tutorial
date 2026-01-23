import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Image Processing Swing UI - Interactive demonstration of image processing
 * algorithms
 * with embedded core algorithms for pixel manipulation and filtering.
 */
public class ImageProcessingUI extends JFrame {
    private ImagePanel imagePanel;
    private BufferedImage originalImage;
    private BufferedImage currentImage;
    private JLabel statsLabel;

    public ImageProcessingUI() {
        setTitle("Image Processing Algorithms Demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setResizable(true);

        // Create main container
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create image display panel
        imagePanel = new ImagePanel();
        JScrollPane scrollPane = new JScrollPane(imagePanel);
        scrollPane.setPreferredSize(new Dimension(600, 500));
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Create control panel
        JPanel controlPanel = createControlPanel();
        mainPanel.add(controlPanel, BorderLayout.EAST);

        // Create stats panel
        statsLabel = new JLabel("<html>Image Stats: <br>Waiting for image...</html>");
        statsLabel.setVerticalAlignment(JLabel.TOP);
        statsLabel.setBorder(BorderFactory.createTitledBorder("Statistics"));
        statsLabel.setPreferredSize(new Dimension(200, 150));
        mainPanel.add(statsLabel, BorderLayout.SOUTH);

        add(mainPanel);

        // Create initial image
        originalImage = createSampleImage(400, 400);
        currentImage = originalImage;
        imagePanel.setImage(currentImage);
        updateStats();
    }

    private JPanel createControlPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder("Image Processing Effects"));
        panel.setPreferredSize(new Dimension(250, 500));

        // Image size selector
        JPanel sizePanel = new JPanel();
        sizePanel.setLayout(new BoxLayout(sizePanel, BoxLayout.Y_AXIS));
        sizePanel.setBorder(BorderFactory.createTitledBorder("Image Size"));

        JButton size200 = createButton("200x200", e -> createNewImage(200, 200));
        JButton size400 = createButton("400x400", e -> createNewImage(400, 400));
        JButton size600 = createButton("600x600", e -> createNewImage(600, 600));

        sizePanel.add(size200);
        sizePanel.add(Box.createVerticalStrut(5));
        sizePanel.add(size400);
        sizePanel.add(Box.createVerticalStrut(5));
        sizePanel.add(size600);
        panel.add(sizePanel);

        panel.add(Box.createVerticalStrut(10));

        // Original image
        JButton resetBtn = createButton("Reset to Original", e -> {
            currentImage = copyImage(originalImage);
            imagePanel.setImage(currentImage);
            updateStats();
        });
        panel.add(resetBtn);

        panel.add(Box.createVerticalStrut(10));

        // Basic operations
        JPanel basicPanel = new JPanel();
        basicPanel.setLayout(new BoxLayout(basicPanel, BoxLayout.Y_AXIS));
        basicPanel.setBorder(BorderFactory.createTitledBorder("Basic Operations"));

        JButton grayscaleBtn = createButton("Grayscale", e -> {
            currentImage = applyGrayscale(currentImage);
            imagePanel.setImage(currentImage);
            updateStats();
        });
        JButton brightenBtn = createButton("Brighten (+30%)", e -> {
            currentImage = adjustBrightness(currentImage, 1.3f);
            imagePanel.setImage(currentImage);
            updateStats();
        });
        JButton darkenBtn = createButton("Darken (-30%)", e -> {
            currentImage = adjustBrightness(currentImage, 0.7f);
            imagePanel.setImage(currentImage);
            updateStats();
        });
        JButton invertBtn = createButton("Invert Colors", e -> {
            currentImage = invertColors(currentImage);
            imagePanel.setImage(currentImage);
            updateStats();
        });

        basicPanel.add(grayscaleBtn);
        basicPanel.add(Box.createVerticalStrut(5));
        basicPanel.add(brightenBtn);
        basicPanel.add(Box.createVerticalStrut(5));
        basicPanel.add(darkenBtn);
        basicPanel.add(Box.createVerticalStrut(5));
        basicPanel.add(invertBtn);
        panel.add(basicPanel);

        panel.add(Box.createVerticalStrut(10));

        // Filter operations
        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.Y_AXIS));
        filterPanel.setBorder(BorderFactory.createTitledBorder("Filters"));

        JButton blurBtn = createButton("Blur", e -> {
            currentImage = applySimpleBlur(currentImage);
            imagePanel.setImage(currentImage);
            updateStats();
        });
        JButton edgeBtn = createButton("Edge Detection", e -> {
            currentImage = applyEdgeDetection(currentImage);
            imagePanel.setImage(currentImage);
            updateStats();
        });
        JButton sharpenBtn = createButton("Sharpen", e -> {
            currentImage = applySharpen(currentImage);
            imagePanel.setImage(currentImage);
            updateStats();
        });

        filterPanel.add(blurBtn);
        filterPanel.add(Box.createVerticalStrut(5));
        filterPanel.add(edgeBtn);
        filterPanel.add(Box.createVerticalStrut(5));
        filterPanel.add(sharpenBtn);
        panel.add(filterPanel);

        panel.add(Box.createVerticalStrut(10));

        // Advanced operations
        JPanel advPanel = new JPanel();
        advPanel.setLayout(new BoxLayout(advPanel, BoxLayout.Y_AXIS));
        advPanel.setBorder(BorderFactory.createTitledBorder("Advanced"));

        JButton thresholdBtn = createButton("Threshold", e -> {
            currentImage = applyThreshold(currentImage, 128);
            imagePanel.setImage(currentImage);
            updateStats();
        });
        JButton sepiaBtn = createButton("Sepia Tone", e -> {
            currentImage = applySepia(currentImage);
            imagePanel.setImage(currentImage);
            updateStats();
        });

        advPanel.add(thresholdBtn);
        advPanel.add(Box.createVerticalStrut(5));
        advPanel.add(sepiaBtn);
        panel.add(advPanel);

        panel.add(Box.createVerticalGlue());

        return panel;
    }

    private JButton createButton(String text, java.awt.event.ActionListener action) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        button.addActionListener(action);
        return button;
    }

    private void createNewImage(int width, int height) {
        originalImage = createSampleImage(width, height);
        currentImage = copyImage(originalImage);
        imagePanel.setImage(currentImage);
        updateStats();
    }

    private void updateStats() {
        ImageStats stats = getImageStatistics(currentImage);
        String html = String.format(
                "<html><b>Image Statistics</b><br>" +
                        "Size: %dx%d pixels<br>" +
                        "Avg Red: %.0f<br>" +
                        "Avg Green: %.0f<br>" +
                        "Avg Blue: %.0f<br>" +
                        "Avg Brightness: %.0f</html>",
                stats.width, stats.height,
                stats.avgRed, stats.avgGreen, stats.avgBlue, stats.avgBrightness);
        statsLabel.setText(html);
    }

    private BufferedImage invertColors(BufferedImage image) {
        BufferedImage result = new BufferedImage(
                image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int rgb = image.getRGB(x, y);
                int r = 255 - ((rgb >> 16) & 0xFF);
                int g = 255 - ((rgb >> 8) & 0xFF);
                int b = 255 - (rgb & 0xFF);

                int newRgb = (r << 16) | (g << 8) | b;
                result.setRGB(x, y, newRgb);
            }
        }

        return result;
    }

    private BufferedImage applySharpen(BufferedImage image) {
        BufferedImage result = new BufferedImage(
                image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);

        int[][] kernel = {
                { 0, -1, 0 },
                { -1, 5, -1 },
                { 0, -1, 0 }
        };

        for (int y = 1; y < image.getHeight() - 1; y++) {
            for (int x = 1; x < image.getWidth() - 1; x++) {
                int r = 0, g = 0, b = 0;

                for (int ky = -1; ky <= 1; ky++) {
                    for (int kx = -1; kx <= 1; kx++) {
                        int rgb = image.getRGB(x + kx, y + ky);
                        int weight = kernel[ky + 1][kx + 1];

                        r += ((rgb >> 16) & 0xFF) * weight;
                        g += ((rgb >> 8) & 0xFF) * weight;
                        b += (rgb & 0xFF) * weight;
                    }
                }

                r = Math.max(0, Math.min(255, r));
                g = Math.max(0, Math.min(255, g));
                b = Math.max(0, Math.min(255, b));

                int newRgb = (r << 16) | (g << 8) | b;
                result.setRGB(x, y, newRgb);
            }
        }

        return result;
    }

    private BufferedImage applyThreshold(BufferedImage image, int threshold) {
        BufferedImage result = new BufferedImage(
                image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int rgb = image.getRGB(x, y);
                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = rgb & 0xFF;

                int brightness = (int) (0.299 * r + 0.587 * g + 0.114 * b);
                int result_color = brightness > threshold ? 255 : 0;

                int newRgb = (result_color << 16) | (result_color << 8) | result_color;
                result.setRGB(x, y, newRgb);
            }
        }

        return result;
    }

    private BufferedImage applySepia(BufferedImage image) {
        BufferedImage result = new BufferedImage(
                image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int rgb = image.getRGB(x, y);
                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = rgb & 0xFF;

                int sepiaR = (int) (0.393 * r + 0.769 * g + 0.189 * b);
                int sepiaG = (int) (0.349 * r + 0.686 * g + 0.168 * b);
                int sepiaB = (int) (0.272 * r + 0.534 * g + 0.131 * b);

                sepiaR = Math.min(255, sepiaR);
                sepiaG = Math.min(255, sepiaG);
                sepiaB = Math.min(255, sepiaB);

                int newRgb = (sepiaR << 16) | (sepiaG << 8) | sepiaB;
                result.setRGB(x, y, newRgb);
            }
        }

        return result;
    }

    private BufferedImage copyImage(BufferedImage image) {
        BufferedImage copy = new BufferedImage(
                image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = copy.createGraphics();
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();
        return copy;
    }

    // ==================== Core Image Processing Algorithms ====================

    /**
     * Create a sample gradient image for demonstration
     */
    public static BufferedImage createSampleImage(int width, int height) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int r = (x * 255) / width;
                int g = (y * 255) / height;
                int b = ((x + y) * 255) / (width + height);
                int rgb = (r << 16) | (g << 8) | b;
                image.setRGB(x, y, rgb);
            }
        }

        return image;
    }

    /**
     * Convert image to grayscale using luminosity formula
     */
    public static BufferedImage applyGrayscale(BufferedImage image) {
        BufferedImage result = new BufferedImage(
                image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int rgb = image.getRGB(x, y);
                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = rgb & 0xFF;

                // Luminosity method: 0.299R + 0.587G + 0.114B
                int gray = (int) (0.299 * r + 0.587 * g + 0.114 * b);
                int grayRgb = (gray << 16) | (gray << 8) | gray;
                result.setRGB(x, y, grayRgb);
            }
        }

        return result;
    }

    /**
     * Adjust image brightness
     */
    public static BufferedImage adjustBrightness(BufferedImage image, float factor) {
        BufferedImage result = new BufferedImage(
                image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int rgb = image.getRGB(x, y);
                int r = Math.min(255, (int) (((rgb >> 16) & 0xFF) * factor));
                int g = Math.min(255, (int) (((rgb >> 8) & 0xFF) * factor));
                int b = Math.min(255, (int) ((rgb & 0xFF) * factor));

                int newRgb = (r << 16) | (g << 8) | b;
                result.setRGB(x, y, newRgb);
            }
        }

        return result;
    }

    /**
     * Apply simple box blur filter
     */
    public static BufferedImage applySimpleBlur(BufferedImage image) {
        BufferedImage result = new BufferedImage(
                image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);

        int[][] kernel = {
                { 1, 1, 1 },
                { 1, 1, 1 },
                { 1, 1, 1 }
        };

        convolve(image, result, kernel, 9);
        return result;
    }

    /**
     * Apply Sobel edge detection filter
     */
    public static BufferedImage applyEdgeDetection(BufferedImage image) {
        BufferedImage result = new BufferedImage(
                image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);

        // Sobel X kernel
        int[][] sobelX = {
                { -1, 0, 1 },
                { -2, 0, 2 },
                { -1, 0, 1 }
        };

        // Sobel Y kernel
        int[][] sobelY = {
                { -1, -2, -1 },
                { 0, 0, 0 },
                { 1, 2, 1 }
        };

        for (int y = 1; y < image.getHeight() - 1; y++) {
            for (int x = 1; x < image.getWidth() - 1; x++) {
                int gx = applySobelKernel(image, x, y, sobelX);
                int gy = applySobelKernel(image, x, y, sobelY);
                int magnitude = (int) Math.sqrt(gx * gx + gy * gy);
                magnitude = Math.min(255, magnitude);

                int edgeRgb = (magnitude << 16) | (magnitude << 8) | magnitude;
                result.setRGB(x, y, edgeRgb);
            }
        }

        return result;
    }

    /**
     * Helper method to apply Sobel kernel
     */
    private static int applySobelKernel(BufferedImage image, int x, int y, int[][] kernel) {
        int sum = 0;
        for (int ky = -1; ky <= 1; ky++) {
            for (int kx = -1; kx <= 1; kx++) {
                int pixelRgb = image.getRGB(x + kx, y + ky);
                int gray = (int) (0.299 * ((pixelRgb >> 16) & 0xFF) +
                        0.587 * ((pixelRgb >> 8) & 0xFF) +
                        0.114 * (pixelRgb & 0xFF));
                sum += gray * kernel[ky + 1][kx + 1];
            }
        }
        return sum;
    }

    /**
     * Apply convolution filter
     */
    private static void convolve(BufferedImage source, BufferedImage dest, int[][] kernel, int divisor) {
        for (int y = 1; y < source.getHeight() - 1; y++) {
            for (int x = 1; x < source.getWidth() - 1; x++) {
                int r = 0, g = 0, b = 0;

                for (int ky = -1; ky <= 1; ky++) {
                    for (int kx = -1; kx <= 1; kx++) {
                        int rgb = source.getRGB(x + kx, y + ky);
                        int weight = kernel[ky + 1][kx + 1];

                        r += ((rgb >> 16) & 0xFF) * weight;
                        g += ((rgb >> 8) & 0xFF) * weight;
                        b += (rgb & 0xFF) * weight;
                    }
                }

                r = Math.min(255, r / divisor);
                g = Math.min(255, g / divisor);
                b = Math.min(255, b / divisor);

                int newRgb = (r << 16) | (g << 8) | b;
                dest.setRGB(x, y, newRgb);
            }
        }
    }

    /**
     * Get statistics about an image
     */
    public static ImageStats getImageStatistics(BufferedImage image) {
        ImageStats stats = new ImageStats();
        stats.width = image.getWidth();
        stats.height = image.getHeight();

        long sumR = 0, sumG = 0, sumB = 0, sumBrightness = 0;
        int pixelCount = 0;

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int rgb = image.getRGB(x, y);
                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = rgb & 0xFF;

                sumR += r;
                sumG += g;
                sumB += b;
                sumBrightness += (0.299 * r + 0.587 * g + 0.114 * b);
                pixelCount++;
            }
        }

        stats.avgRed = (double) sumR / pixelCount;
        stats.avgGreen = (double) sumG / pixelCount;
        stats.avgBlue = (double) sumB / pixelCount;
        stats.avgBrightness = (double) sumBrightness / pixelCount;

        return stats;
    }

    /**
     * Inner class to hold image statistics
     */
    public static class ImageStats {
        public int width;
        public int height;
        public double avgRed;
        public double avgGreen;
        public double avgBlue;
        public double avgBrightness;
    }

    /**
     * Image display panel
     */
    private static class ImagePanel extends JPanel {
        private BufferedImage image;
        private double scale = 1.0;

        public void setImage(BufferedImage img) {
            this.image = img;
            scale = 1.0;
            revalidate();
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (image != null) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                        RenderingHints.VALUE_INTERPOLATION_BICUBIC);

                int displayWidth = (int) (image.getWidth() * scale);
                int displayHeight = (int) (image.getHeight() * scale);

                int x = (getWidth() - displayWidth) / 2;
                int y = (getHeight() - displayHeight) / 2;

                g2d.drawImage(image, x, y, displayWidth, displayHeight, null);
            }
        }

        @Override
        public Dimension getPreferredSize() {
            if (image != null) {
                return new Dimension(image.getWidth(), image.getHeight());
            }
            return new Dimension(400, 400);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ImageProcessingUI ui = new ImageProcessingUI();
            ui.setVisible(true);
        });
    }
}
