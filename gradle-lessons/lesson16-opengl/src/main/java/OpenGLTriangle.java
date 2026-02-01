import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

/**
 * Lesson 16: OpenGL Basics - Drawing a Triangle
 * 
 * This example demonstrates:
 * - Initializing GLFW window
 * - Setting up OpenGL context
 * - Creating and compiling shaders (vertex and fragment)
 * - Creating VAO, VBO for vertex data
 * - Rendering a colored triangle
 * - Basic OpenGL rendering loop
 */
public class OpenGLTriangle {
    
    // Window handle
    private long window;
    
    // Shader program ID
    private int shaderProgram;
    
    // Vertex Array Object and Vertex Buffer Object IDs
    private int VAO, VBO;
    
    public void run() {
        System.out.println("Hello LWJGL " + Version.getVersion() + "!");
        
        init();
        loop();
        
        // Free resources
        cleanup();
    }
    
    private void init() {
        // Setup error callback
        GLFWErrorCallback.createPrint(System.err).set();
        
        // Initialize GLFW
        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }
        
        // Configure GLFW
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // Window will stay hidden until shown
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // Window will be resizable
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE); // macOS compatibility
        
        // Create the window
        window = glfwCreateWindow(800, 600, "OpenGL Triangle - Lesson 16", NULL, NULL);
        if (window == NULL) {
            throw new RuntimeException("Failed to create the GLFW window");
        }
        
        // Setup key callback
        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE) {
                glfwSetWindowShouldClose(window, true);
            }
        });
        
        // Get the thread stack and push a new frame
        try (MemoryStack stack = stackPush()) {
            IntBuffer pWidth = stack.mallocInt(1);
            IntBuffer pHeight = stack.mallocInt(1);
            
            // Get the window size
            glfwGetWindowSize(window, pWidth, pHeight);
            
            // Get the resolution of the primary monitor
            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
            
            // Center the window
            glfwSetWindowPos(
                window,
                (vidmode.width() - pWidth.get(0)) / 2,
                (vidmode.height() - pHeight.get(0)) / 2
            );
        }
        
        // Make the OpenGL context current
        glfwMakeContextCurrent(window);
        
        // Enable v-sync
        glfwSwapInterval(1);
        
        // Make the window visible
        glfwShowWindow(window);
        
        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        GL.createCapabilities();
        
        // Setup shaders
        setupShaders();
        
        // Setup vertex data
        setupVertexData();
    }
    
    private void setupShaders() {
        // Vertex shader source code
        String vertexShaderSource = "#version 330 core\n" +
            "layout (location = 0) in vec3 aPos;\n" +
            "layout (location = 1) in vec3 aColor;\n" +
            "out vec3 vertexColor;\n" +
            "void main() {\n" +
            "   gl_Position = vec4(aPos.x, aPos.y, aPos.z, 1.0);\n" +
            "   vertexColor = aColor;\n" +
            "}\n";
        
        // Fragment shader source code
        String fragmentShaderSource = "#version 330 core\n" +
            "in vec3 vertexColor;\n" +
            "out vec4 FragColor;\n" +
            "void main() {\n" +
            "   FragColor = vec4(vertexColor, 1.0f);\n" +
            "}\n";
        
        // Compile vertex shader
        int vertexShader = glCreateShader(GL_VERTEX_SHADER);
        glShaderSource(vertexShader, vertexShaderSource);
        glCompileShader(vertexShader);
        
        // Check for compilation errors
        int success = glGetShaderi(vertexShader, GL_COMPILE_STATUS);
        if (success == GL_FALSE) {
            int len = glGetShaderi(vertexShader, GL_INFO_LOG_LENGTH);
            System.err.println("ERROR: Vertex shader compilation failed!");
            System.err.println(glGetShaderInfoLog(vertexShader, len));
            System.exit(-1);
        }
        
        // Compile fragment shader
        int fragmentShader = glCreateShader(GL_FRAGMENT_SHADER);
        glShaderSource(fragmentShader, fragmentShaderSource);
        glCompileShader(fragmentShader);
        
        // Check for compilation errors
        success = glGetShaderi(fragmentShader, GL_COMPILE_STATUS);
        if (success == GL_FALSE) {
            int len = glGetShaderi(fragmentShader, GL_INFO_LOG_LENGTH);
            System.err.println("ERROR: Fragment shader compilation failed!");
            System.err.println(glGetShaderInfoLog(fragmentShader, len));
            System.exit(-1);
        }
        
        // Create shader program
        shaderProgram = glCreateProgram();
        glAttachShader(shaderProgram, vertexShader);
        glAttachShader(shaderProgram, fragmentShader);
        glLinkProgram(shaderProgram);
        
        // Check for linking errors
        success = glGetProgrami(shaderProgram, GL_LINK_STATUS);
        if (success == GL_FALSE) {
            int len = glGetProgrami(shaderProgram, GL_INFO_LOG_LENGTH);
            System.err.println("ERROR: Shader program linking failed!");
            System.err.println(glGetProgramInfoLog(shaderProgram, len));
            System.exit(-1);
        }
        
        // Delete shaders (they're linked into the program now)
        glDeleteShader(vertexShader);
        glDeleteShader(fragmentShader);
    }
    
    private void setupVertexData() {
        // Define triangle vertices with positions and colors
        // Format: x, y, z, r, g, b
        float[] vertices = OpenGLUtils.createTriangleVertices(
            -0.5f, -0.5f, 0.0f,  1.0f, 0.0f, 0.0f, // Bottom left - Red
             0.5f, -0.5f, 0.0f,  0.0f, 1.0f, 0.0f, // Bottom right - Green
             0.0f,  0.5f, 0.0f,  0.0f, 0.0f, 1.0f  // Top - Blue
        );
        
        // Generate VAO (Vertex Array Object)
        VAO = glGenVertexArrays();
        // Generate VBO (Vertex Buffer Object)
        VBO = glGenBuffers();
        
        // Bind VAO first, then bind and set vertex buffer(s)
        glBindVertexArray(VAO);
        
        // Bind VBO and copy vertex data into it
        glBindBuffer(GL_ARRAY_BUFFER, VBO);
        FloatBuffer vertexBuffer = MemoryUtil.memAllocFloat(vertices.length);
        vertexBuffer.put(vertices).flip();
        glBufferData(GL_ARRAY_BUFFER, vertexBuffer, GL_STATIC_DRAW);
        MemoryUtil.memFree(vertexBuffer);
        
        // Position attribute (location = 0)
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 6 * Float.BYTES, 0);
        glEnableVertexAttribArray(0);
        
        // Color attribute (location = 1)
        glVertexAttribPointer(1, 3, GL_FLOAT, false, 6 * Float.BYTES, 3 * Float.BYTES);
        glEnableVertexAttribArray(1);
        
        // Unbind VBO and VAO
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindVertexArray(0);
    }
    
    private void loop() {
        // Set the clear color (background color)
        glClearColor(0.2f, 0.3f, 0.3f, 1.0f);
        
        // Run the rendering loop until the user closes the window
        while (!glfwWindowShouldClose(window)) {
            // Poll for window events
            glfwPollEvents();
            
            // Clear the framebuffer
            glClear(GL_COLOR_BUFFER_BIT);
            
            // Use our shader program
            glUseProgram(shaderProgram);
            
            // Bind VAO and draw
            glBindVertexArray(VAO);
            glDrawArrays(GL_TRIANGLES, 0, 3);
            
            // Swap the color buffers
            glfwSwapBuffers(window);
        }
    }
    
    private void cleanup() {
        // Free resources
        glDeleteVertexArrays(VAO);
        glDeleteBuffers(VBO);
        glDeleteProgram(shaderProgram);
        
        // Free the window callbacks and destroy the window
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);
        
        // Terminate GLFW and free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }
    
    public static void main(String[] args) {
        new OpenGLTriangle().run();
    }
}
