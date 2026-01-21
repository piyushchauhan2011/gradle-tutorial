# Lesson 15: Image Processing and Manipulation in Java

## Description
This lesson covers fundamental image processing techniques and algorithms in Java. Learn how to manipulate pixel data, apply filters, detect edges, and analyze image properties. This lesson includes an interactive Swing GUI to visualize and experiment with various image processing algorithms in real-time.

## Key Concepts

### 1. Image Representation
- **BufferedImage**: Java's standard way to represent images in memory
- **Pixel Data**: Understanding RGB color model (Red, Green, Blue channels)
- **Image Coordinates**: How to access and modify individual pixels

### 2. Basic Operations
- **Grayscale Conversion**: Converting color images to grayscale using luminosity formula
- **Brightness Adjustment**: Modifying pixel intensity values
- **Color Space Conversions**: Working with different color representations

### 3. Filtering Techniques
- **Convolution Filters**: Applying kernels to images for effects like blur
- **Edge Detection**: Using Sobel filters to identify image boundaries
- **Kernel Operations**: Understanding 3x3 kernels and their applications

### 4. Image Analysis
- **Statistical Analysis**: Computing average pixel values and brightness
- **Histogram Analysis**: Understanding pixel distribution
- **Image Metrics**: Calculating image properties

### 5. Advanced Topics (Exercises)
- Threshold-based image segmentation
- Morphological operations (erosion, dilation)
- Histogram equalization for contrast enhancement
- Custom filter kernels

## Runnable Examples

### Run the Interactive Swing UI (RECOMMENDED):
```bash
./gradlew -p lesson15-image-processing run
```

This launches an interactive GUI application where you can:
- **View images** in real-time
- **Apply filters** with one click:
  - Basic: Grayscale, Brighten, Darken, Invert Colors
  - Filters: Blur, Edge Detection, Sharpen
  - Advanced: Threshold, Sepia Tone
- **Change image size**: 200x200, 400x400, 600x600
- **Reset to original** at any time
- **View live statistics**: Average RGB values and brightness

### Run the console demo:
```bash
./gradlew -p lesson15-image-processing run --args=demo
```

This demonstrates various effects in the console without GUI.

### Run tests:
```bash
./gradlew -p lesson15-image-processing test
```

### Build the project:
```bash
./gradlew -p lesson15-image-processing build
```

## Code Structure

### Main Classes

#### `ImageProcessingUI` (Interactive Swing Application)
- Provides a graphical interface to experiment with image processing
- Real-time image manipulation and effect application
- Live statistics display
- Multiple image size options for testing

Components:
- `ImagePanel`: Custom Swing component for displaying images
- Control panel with organized buttons for different effects
- Statistics panel showing image metrics

#### `ImageProcessingDemo` (Core algorithms library)
- `createSampleImage()`: Generates a sample gradient image for testing
- `applyGrayscale()`: Converts images to grayscale
- `adjustBrightness()`: Modifies image brightness
- `applySimpleBlur()`: Applies box blur filter
- `applyEdgeDetection()`: Implements Sobel edge detection
- `getImageStatistics()`: Analyzes image properties

### Additional Methods in ImageProcessingUI
- `invertColors()`: Inverts RGB values
- `applySharpen()`: Applies sharpening kernel
- `applyThreshold()`: Binary thresholding
- `applySepia()`: Vintage sepia tone effect

### Helper Methods
- `convolve()`: Generic convolution filter application
- `applySobelKernel()`: Applies Sobel kernel for edge detection

## Exercises

### Beginner
1. **Threshold Filter**: Create a method that converts an image to black and white based on a brightness threshold
   ```java
   BufferedImage applyThreshold(BufferedImage image, int threshold)
   ```

2. **Invert Colors**: Create a method that inverts all pixel colors (R' = 255-R, etc.)
   ```java
   BufferedImage invertColors(BufferedImage image)
   ```

3. **Rotate Image**: Implement 90-degree rotation
   ```java
   BufferedImage rotateImage(BufferedImage image)
   ```

### Intermediate
4. **Gaussian Blur**: Implement Gaussian blur instead of simple box blur
   - Use a Gaussian kernel with appropriate weights
   - Better quality blur than box blur

5. **Histogram Equalization**: Improve image contrast
   - Calculate histogram of pixel values
   - Redistribute pixel intensities evenly

6. **Resize Image**: Implement image scaling
   ```java
   BufferedImage resizeImage(BufferedImage image, int newWidth, int newHeight)
   ```

### Advanced
7. **Morphological Operations**: Implement erosion and dilation
   ```java
   BufferedImage erode(BufferedImage image)
   BufferedImage dilate(BufferedImage image)
   ```

8. **Sepia Tone**: Apply vintage sepia effect
   - Apply weighted transformation to RGB channels
   - Create old-photo appearance

9. **Canny Edge Detection**: Implement advanced edge detection
   - Gaussian blur
   - Gradient calculation
   - Non-maximum suppression
   - Double threshold and edge tracking

10. **Custom Kernel Filter**: Design and apply custom convolution kernels
    - Sharpen filter
    - Motion blur filter
    - Emboss effect

## Key Takeaways

1. **Pixel Manipulation**: Direct access to pixel data enables powerful image processing
2. **Mathematical Operations**: Image filters are mathematical operations applied to pixel neighborhoods
3. **Color Spaces**: Different color representations are useful for different tasks
4. **Performance Considerations**: Pixel-by-pixel operations can be slow; consider optimization techniques
5. **Practical Applications**: Text recognition, medical imaging, computer vision, photo editing
6. **GUI Development**: Swing provides tools to build interactive image processing applications

## Swing UI Features

The interactive Swing application (`ImageProcessingUI`) provides:

- **Real-time visualization**: See effects applied instantly
- **Modular button layout**: Organized controls by category
- **Live statistics**: Immediate feedback on image properties
- **Multiple image sizes**: Test with different resolutions
- **Easy extension**: Add new filters by adding buttons and methods

### How to Add New Effects

1. Create a new method in `ImageProcessingUI` that takes a `BufferedImage` and returns a processed `BufferedImage`
2. Add a button in `createControlPanel()` that calls your method
3. Update the image and statistics display

Example:
```java
JButton customBtn = createButton("My Effect", e -> {
    currentImage = applyMyEffect(currentImage);
    imagePanel.setImage(currentImage);
    updateStats();
});
```

## Additional Resources

### Java Image APIs
- `java.awt.image.BufferedImage`: Core image class
- `javax.imageio.ImageIO`: Reading/writing image files
- `java.awt.image.BufferedImageOp`: Framework for image operations
- `javax.swing.JPanel`: Custom painting in Swing

### Concepts to Explore
- FFT (Fast Fourier Transform) for frequency domain analysis
- Deep learning for advanced image understanding
- GPU acceleration for large-scale processing
- Real-time video processing

## Building and Running

### Prerequisites
- Java Development Kit (JDK) 11 or later
- Gradle (included as wrapper `./gradlew`)

### Build
```bash
./gradlew -p lesson15-image-processing build
```

### Run Interactive UI
```bash
./gradlew -p lesson15-image-processing run
```

### Run Tests
```bash
./gradlew -p lesson15-image-processing test
```

### Run with Gradle Task
```bash
./gradlew -p lesson15-image-processing clean build test run
```

## Notes

- Java's standard `BufferedImage` is sufficient for most basic operations
- For production use with large images, consider memory optimization and multi-threading
- Most commercial image processing (like Photoshop) uses similar underlying algorithms
- Understanding these basics is fundamental to computer vision and machine learning applications
- The Swing UI is responsive and handles large images efficiently up to ~1000x1000 pixels
