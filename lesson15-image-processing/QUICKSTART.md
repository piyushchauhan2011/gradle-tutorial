# Image Processing Swing UI - Quick Start Guide

## What's Included

✅ **Interactive Swing GUI Application** - Visual image processing demo with real-time effects  
✅ **Core Image Processing Library** - Reusable algorithms and filters  
✅ **Comprehensive Unit Tests** - Test coverage for all major functions  
✅ **Full Source Code** - Well-documented, educational code  

## Quick Commands

### Launch the Interactive UI
```bash
./g -p lesson15-image-processing run
```
This opens an interactive window where you can:
- View and manipulate images in real-time
- Apply various image processing filters with one click
- See live statistics about the image
- Change image sizes and reset to original

### Build the Project
```bash
./g -p lesson15-image-processing build
```

### Run Tests
```bash
./g -p lesson15-image-processing test
```

### Clean and Rebuild
```bash
./g -p lesson15-image-processing clean build
```

## Available Effects in the UI

### Image Sizes
- 200x200 pixels
- 400x400 pixels (default)
- 600x600 pixels

### Basic Operations
- **Grayscale** - Convert to grayscale using luminosity formula
- **Brighten (+30%)** - Increase brightness
- **Darken (-30%)** - Decrease brightness
- **Invert Colors** - Reverse all RGB values

### Filters
- **Blur** - Apply box blur filter
- **Edge Detection** - Sobel filter to find edges
- **Sharpen** - Enhance details

### Advanced Effects
- **Threshold** - Convert to black and white
- **Sepia Tone** - Vintage photo effect

### Image Statistics
Real-time display of:
- Image dimensions
- Average Red, Green, Blue values
- Average Brightness

## Project Structure

```
lesson15-image-processing/
├── build.gradle                    # Gradle build configuration (Groovy)
├── build.gradle.kts                # Gradle build configuration (Kotlin)
├── README.md                       # Full documentation
├── src/
│   ├── main/java/
│   │   ├── ImageProcessingDemo.java    # Core algorithms
│   │   └── ImageProcessingUI.java      # Swing GUI application
│   └── test/java/
│       └── ImageProcessingTest.java    # Unit tests
```

## Class Overview

### ImageProcessingUI (Main Application)
The interactive Swing GUI with:
- `createControlPanel()` - Creates all UI buttons and controls
- `updateStats()` - Updates displayed image statistics
- `ImagePanel` (inner class) - Custom component for displaying images
- Various filter methods: `invertColors()`, `applySharpen()`, `applyThreshold()`, `applySepia()`

### ImageProcessingDemo (Core Library)
Reusable image processing algorithms:
- `createSampleImage(width, height)` - Generate test images
- `applyGrayscale(image)` - Grayscale conversion
- `adjustBrightness(image, factor)` - Brightness control
- `applySimpleBlur(image)` - Blur filter
- `applyEdgeDetection(image)` - Sobel edge detection
- `getImageStatistics(image)` - Analyze image properties

### ImageProcessingTest (Unit Tests)
Tests for:
- Image creation and dimensions
- Grayscale correctness
- Brightness adjustment
- Filter operations
- Statistical analysis

## How to Add New Effects

### Step 1: Create the Effect Method
Add a method to `ImageProcessingUI.java`:
```java
private BufferedImage applyMyEffect(BufferedImage image) {
    BufferedImage result = new BufferedImage(
        image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
    
    // Your image processing code here
    
    return result;
}
```

### Step 2: Add a Button
In `createControlPanel()`, add:
```java
JButton myEffectBtn = createButton("My Effect", e -> {
    currentImage = applyMyEffect(currentImage);
    imagePanel.setImage(currentImage);
    updateStats();
});
myPanel.add(myEffectBtn);
```

### Step 3: Rebuild and Test
```bash
./g -p lesson15-image-processing build run
```

## Educational Value

This lesson demonstrates:
- **Pixel-level image manipulation** in Java
- **Convolution filters** and their implementation
- **Color space transformations** (RGB, grayscale, sepia)
- **Swing GUI development** for interactive applications
- **Unit testing** for image processing code
- **Gradle multi-module project structure**

## Performance Tips

For large images or real-time processing:
- Use smaller image sizes (200-400 pixels)
- Consider image resizing before complex operations
- Multi-threaded processing for filters on large images
- Use `SwingWorker` for long-running operations

## Troubleshooting

### UI doesn't appear
- Ensure Java AWT/Swing is properly installed
- Try running from terminal: `./g -p lesson15-image-processing run`

### Slow performance
- Use smaller image sizes
- Reduce filter complexity
- Run on a machine with more RAM

### Build failures
- Clean and rebuild: `./g -p lesson15-image-processing clean build`
- Check Java version: `java -version` (should be 11+)

## Next Steps

1. **Explore the Code** - Read through `ImageProcessingUI.java` and `ImageProcessingDemo.java`
2. **Try the UI** - Run the application and experiment with different effects
3. **Add Effects** - Implement histogram equalization, rotation, or other filters
4. **Write Tests** - Add unit tests for your custom effects
5. **Optimize** - Improve performance using multi-threading or GPU acceleration

## Resources

- [Java BufferedImage Documentation](https://docs.oracle.com/javase/8/docs/api/java/awt/image/BufferedImage.html)
- [Java Swing Tutorial](https://docs.oracle.com/javase/tutorial/uiswing/)
- Image Processing Concepts: Convolution, edge detection, color spaces
- Computer Vision Basics: Filters, kernels, and transformations
