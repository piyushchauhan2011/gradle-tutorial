# Lesson 16: OpenGL Graphics Programming with Java

## Description
This lesson introduces you to OpenGL graphics programming using Java and LWJGL (Lightweight Java Game Library). Learn how to create windows, set up OpenGL contexts, write shaders, and render 2D and 3D graphics. This lesson covers fundamental graphics programming concepts that are essential for game development, visualization, and interactive applications.

## Key Concepts

### 1. OpenGL Overview
- **What is OpenGL?**: Cross-platform graphics API for rendering 2D and 3D graphics
- **Graphics Pipeline**: Understanding the stages from vertices to pixels
- **LWJGL**: Java bindings for OpenGL, GLFW, and other native libraries
- **OpenGL Versions**: Core profile vs compatibility profile

### 2. Window Management (GLFW)
- **GLFW**: Library for creating windows and handling input
- **Window Creation**: Setting up a window with OpenGL context
- **Event Handling**: Keyboard and mouse input callbacks
- **Context Management**: Making OpenGL context current

### 3. Shaders
- **Vertex Shader**: Processes vertex data (positions, colors, etc.)
- **Fragment Shader**: Determines pixel colors
- **Shader Compilation**: Compiling GLSL (OpenGL Shading Language) code
- **Shader Programs**: Linking shaders into executable programs
- **Uniforms**: Passing data from CPU to GPU

### 4. Vertex Data
- **VAO (Vertex Array Object)**: Stores vertex attribute configurations
- **VBO (Vertex Buffer Object)**: Stores vertex data in GPU memory
- **EBO (Element Buffer Object)**: Stores indices for indexed drawing
- **Vertex Attributes**: Defining how vertex data is interpreted
- **Interleaved Data**: Mixing position, color, and other attributes

### 5. Rendering
- **Render Loop**: The main game/application loop
- **Clear Operations**: Clearing color and depth buffers
- **Draw Calls**: `glDrawArrays()` and `glDrawElements()`
- **Primitives**: Points, lines, triangles
- **Double Buffering**: Smooth animation with front/back buffers

### 6. 3D Graphics Basics
- **Depth Testing**: Handling z-ordering for 3D scenes
- **Transformations**: Rotation, translation, scaling
- **Coordinate Systems**: Understanding 3D space
- **Projection**: Converting 3D to 2D screen coordinates

## Runnable Examples

### Example 1: Basic Triangle (`OpenGLTriangle`)
A simple 2D colored triangle demonstrating the fundamentals:

```bash
./gradlew -p lesson16-opengl run
```

This example shows:
- Window creation with GLFW
- Basic shader setup (vertex and fragment shaders)
- VAO and VBO creation
- Rendering a triangle with vertex colors
- Basic render loop

**Key Learning Points:**
- How to initialize LWJGL and GLFW
- Creating and compiling shaders
- Setting up vertex data
- The rendering pipeline basics

### Example 2: Rotating 3D Cube (`OpenGLCube`)
A more advanced example with 3D geometry:

```bash
./gradlew -p lesson16-opengl run --args="cube"
```

Or modify `build.gradle` to change the main class:
```groovy
application {
    mainClass = 'OpenGLCube'
}
```

This example demonstrates:
- 3D geometry (cube with 8 vertices)
- Index buffers (EBO) for efficient rendering
- Depth testing for proper 3D rendering
- Uniform variables for animation
- Rotation transformations

**Key Learning Points:**
- Indexed drawing with EBO
- Depth buffer and depth testing
- Using uniforms for dynamic values
- 3D transformations

## Code Structure

### `OpenGLTriangle.java` - Basic Triangle Example

#### Initialization (`init()`)
1. **GLFW Setup**: Initialize GLFW and create error callback
2. **Window Configuration**: Set OpenGL version and profile hints
3. **Window Creation**: Create window with specified size
4. **Context Setup**: Make OpenGL context current
5. **LWJGL Capabilities**: Load OpenGL function pointers
6. **Shader Setup**: Compile and link shaders
7. **Vertex Data**: Create VAO/VBO and upload vertex data

#### Shader Setup (`setupShaders()`)
- **Vertex Shader**: Transforms vertex positions and passes colors
- **Fragment Shader**: Outputs final pixel colors
- **Error Checking**: Validates shader compilation and linking

#### Vertex Data (`setupVertexData()`)
- **Vertex Format**: Position (x,y,z) + Color (r,g,b) = 6 floats per vertex
- **VAO**: Stores vertex attribute configuration
- **VBO**: Stores actual vertex data in GPU memory
- **Attribute Pointers**: Tells OpenGL how to interpret the data

#### Render Loop (`loop()`)
1. Clear the framebuffer
2. Use shader program
3. Bind VAO
4. Draw triangles
5. Swap buffers
6. Poll events

### `OpenGLCube.java` - 3D Cube Example

#### Additional Concepts
- **EBO (Element Buffer Object)**: Stores indices to reuse vertices
- **Depth Testing**: `glEnable(GL_DEPTH_TEST)` for 3D rendering
- **Uniform Variables**: Passing rotation angle to shader
- **Indexed Drawing**: `glDrawElements()` instead of `glDrawArrays()`

## Exercises

### Beginner

1. **Change Triangle Colors**
   - Modify the vertex colors in `OpenGLTriangle.java`
   - Try different color combinations
   - Experiment with color interpolation

2. **Draw Multiple Triangles**
   - Add more vertices to create multiple triangles
   - Try arranging them in a pattern

3. **Modify Window Size**
   - Change the window dimensions
   - Observe how coordinates map to screen space

4. **Add Keyboard Controls**
   - Add keys to change colors dynamically
   - Add keys to move the triangle

### Intermediate

5. **Create a Rectangle**
   - Draw a rectangle using two triangles
   - Use an EBO to share vertices

6. **Add Texture Coordinates**
   - Add texture coordinates to vertex data
   - Modify shaders to accept texture coordinates
   - (Advanced: Load and bind a texture)

7. **Implement Translation**
   - Add uniform for translation offset
   - Allow moving shapes with keyboard input

8. **Create a Star Shape**
   - Design vertices for a star
   - Render it with appropriate colors

### Advanced

9. **Implement Perspective Projection**
   - Add projection matrix uniform
   - Use proper 3D perspective
   - Create a camera system

10. **Load 3D Models**
    - Use LWJGL's ASSIMP bindings to load .obj files
    - Render loaded models

11. **Implement Lighting**
    - Add normal vectors to vertices
    - Implement Phong or Blinn-Phong lighting
    - Add light sources

12. **Create a Simple Game**
    - Multiple objects
    - Collision detection
    - Score system
    - Game states

13. **Post-Processing Effects**
    - Render to framebuffer
    - Apply effects like blur, grayscale
    - Implement multiple render passes

## Platform-Specific Notes

### macOS
The build files automatically detect your platform and use the correct native libraries:
- **Apple Silicon (ARM64)**: Uses `natives-macos-arm64`
- **Intel Mac (x64)**: Uses `natives-macos`

**Important**: macOS requires GLFW to run on the main thread. The build files automatically add the `-XstartOnFirstThread` JVM argument when running on macOS. This is handled automatically - you don't need to do anything.

The platform detection happens automatically based on your system properties. If you need to override it manually, you can modify the `lwjglNatives` variable in `build.gradle`:

```groovy
def lwjglNatives = "natives-macos-arm64"  // Force ARM64
def lwjglNatives = "natives-macos"       // Force x64
def lwjglNatives = "natives-windows"     // For Windows
def lwjglNatives = "natives-linux"       // For Linux
```

### Windows
- Use `natives-windows` for the native libraries
- May need Visual C++ Redistributables

### Linux
- Use `natives-linux` for the native libraries
- May need to install OpenGL development libraries:
  ```bash
  sudo apt-get install libgl1-mesa-dev
  ```

## Key Takeaways

1. **Graphics Pipeline**: Understanding the flow from vertices → shaders → pixels
2. **GPU Programming**: Shaders run on GPU, enabling parallel processing
3. **Memory Management**: VAO/VBO/EBO efficiently manage GPU memory
4. **State Machine**: OpenGL is a state machine - bind objects before using
5. **Performance**: Minimize state changes and draw calls for better performance
6. **Modern OpenGL**: Core profile uses programmable pipeline (shaders required)
7. **LWJGL**: Provides Java bindings for native OpenGL libraries

## Common Issues and Solutions

### Issue: "Unable to initialize GLFW"
- **Solution**: Ensure you have the correct native libraries for your platform
- Check that `lwjglNatives` matches your OS

### Issue: "Shader compilation failed"
- **Solution**: Check shader source code syntax
- Ensure OpenGL version matches shader version (`#version 330 core`)
- Check error logs printed to console

### Issue: "Nothing renders / black screen"
- **Solution**: 
  - Verify shader program is being used (`glUseProgram`)
  - Check that VAO is bound before drawing
  - Ensure vertex data is correct
  - Check clear color isn't same as object color

### Issue: "Window doesn't appear"
- **Solution**: Ensure `glfwShowWindow(window)` is called
- Check that render loop is running

### Issue: "GLFW may only be used on the main thread" (macOS)
- **Solution**: The build files automatically add `-XstartOnFirstThread` JVM argument on macOS
- This is required for GLFW to work properly on macOS
- If running manually, use: `java -XstartOnFirstThread -cp ... OpenGLTriangle`

## Building and Running

### Prerequisites
- Java Development Kit (JDK) 11 or later
- Gradle (included as wrapper `./gradlew`)
- OpenGL 3.3+ compatible graphics card/driver

### Build
```bash
./gradlew -p lesson16-opengl build
```

### Run Triangle Example
```bash
./gradlew -p lesson16-opengl run
```

### Run Cube Example
Modify `build.gradle` to change main class, or create a custom task.

### Run Tests
```bash
./gradlew -p lesson16-opengl test
```

## Additional Resources

### LWJGL Documentation
- [LWJGL Official Website](https://www.lwjgl.org/)
- [LWJGL Wiki](https://github.com/LWJGL/lwjgl3-wiki/wiki)
- [LWJGL Examples](https://github.com/LWJGL/lwjgl3-demos)

### OpenGL Learning
- [LearnOpenGL](https://learnopengl.com/) - Excellent OpenGL tutorial (C++ but concepts apply)
- [OpenGL Reference](https://www.khronos.org/opengl/)
- [GLSL Reference](https://www.khronos.org/opengl/wiki/OpenGL_Shading_Language)

### Shader Programming
- [The Book of Shaders](https://thebookofshaders.com/)
- [ShaderToy](https://www.shadertoy.com/) - Interactive shader playground

### Concepts to Explore Next
- **Textures**: Loading and applying images to surfaces
- **Model Loading**: Using ASSIMP to load 3D models
- **Advanced Lighting**: PBR (Physically Based Rendering)
- **Shadow Mapping**: Real-time shadows
- **Particle Systems**: Visual effects
- **Framebuffers**: Render-to-texture, post-processing
- **Geometry Shaders**: Generate geometry in GPU
- **Compute Shaders**: General-purpose GPU computing

## Next Steps

After mastering these basics, consider:
1. **Game Development**: Use these concepts to build games
2. **Visualization**: Create data visualization tools
3. **3D Modeling Tools**: Build custom 3D editors
4. **VR/AR**: Extend to virtual/augmented reality
5. **Graphics Research**: Explore advanced rendering techniques

## Notes

- OpenGL is a low-level API - you manage most details yourself
- Modern OpenGL (3.3+) requires shaders - no fixed-function pipeline
- LWJGL provides thin Java wrappers around native OpenGL functions
- Performance is critical in graphics programming - profile your code
- Understanding the graphics pipeline is essential for debugging
- Shader debugging can be challenging - use print statements or visualization tools
- Consider using a graphics debugger like RenderDoc for complex issues
