// import org.lwjgl.*;
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
 * Lesson 16: OpenGL Advanced - Drawing a Rotating 3D Cube
 * 
 * This example demonstrates:
 * - 3D transformations (rotation)
 * - Index buffers (EBO - Element Buffer Object)
 * - Depth testing
 * - Matrix transformations
 * - More complex geometry
 */
public class OpenGLCube {
    
    private long window;
    private int shaderProgram;
    private int VAO, VBO, EBO;
    
    // Rotation angle
    private float rotation = 0.0f;
    
    public void run() {
        System.out.println("OpenGL 3D Cube Example");
        
        init();
        loop();
        cleanup();
    }
    
    private void init() {
        GLFWErrorCallback.createPrint(System.err).set();
        
        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }
        
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE);
        
        window = glfwCreateWindow(800, 600, "OpenGL Rotating Cube - Lesson 16", NULL, NULL);
        if (window == NULL) {
            throw new RuntimeException("Failed to create the GLFW window");
        }
        
        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE) {
                glfwSetWindowShouldClose(window, true);
            }
        });
        
        try (MemoryStack stack = stackPush()) {
            IntBuffer pWidth = stack.mallocInt(1);
            IntBuffer pHeight = stack.mallocInt(1);
            
            glfwGetWindowSize(window, pWidth, pHeight);
            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
            
            glfwSetWindowPos(
                window,
                (vidmode.width() - pWidth.get(0)) / 2,
                (vidmode.height() - pHeight.get(0)) / 2
            );
        }
        
        glfwMakeContextCurrent(window);
        glfwSwapInterval(1);
        glfwShowWindow(window);
        
        GL.createCapabilities();
        
        setupShaders();
        setupVertexData();
        
        // Enable depth testing for 3D
        glEnable(GL_DEPTH_TEST);
    }
    
    private void setupShaders() {
        // Vertex shader with simple rotation
        String vertexShaderSource = "#version 330 core\n" +
            "layout (location = 0) in vec3 aPos;\n" +
            "layout (location = 1) in vec3 aColor;\n" +
            "out vec3 vertexColor;\n" +
            "uniform float rotation;\n" +
            "void main() {\n" +
            "   float c = cos(rotation);\n" +
            "   float s = sin(rotation);\n" +
            "   mat2 rot = mat2(c, -s, s, c);\n" +
            "   vec2 rotated = rot * aPos.xy;\n" +
            "   gl_Position = vec4(rotated.x, rotated.y, aPos.z * 0.5, 1.0);\n" +
            "   vertexColor = aColor;\n" +
            "}\n";
        
        String fragmentShaderSource = "#version 330 core\n" +
            "in vec3 vertexColor;\n" +
            "out vec4 FragColor;\n" +
            "void main() {\n" +
            "   FragColor = vec4(vertexColor, 1.0f);\n" +
            "}\n";
        
        int vertexShader = glCreateShader(GL_VERTEX_SHADER);
        glShaderSource(vertexShader, vertexShaderSource);
        glCompileShader(vertexShader);
        
        int success = glGetShaderi(vertexShader, GL_COMPILE_STATUS);
        if (success == GL_FALSE) {
            int len = glGetShaderi(vertexShader, GL_INFO_LOG_LENGTH);
            System.err.println("ERROR: Vertex shader compilation failed!");
            System.err.println(glGetShaderInfoLog(vertexShader, len));
            System.exit(-1);
        }
        
        int fragmentShader = glCreateShader(GL_FRAGMENT_SHADER);
        glShaderSource(fragmentShader, fragmentShaderSource);
        glCompileShader(fragmentShader);
        
        success = glGetShaderi(fragmentShader, GL_COMPILE_STATUS);
        if (success == GL_FALSE) {
            int len = glGetShaderi(fragmentShader, GL_INFO_LOG_LENGTH);
            System.err.println("ERROR: Fragment shader compilation failed!");
            System.err.println(glGetShaderInfoLog(fragmentShader, len));
            System.exit(-1);
        }
        
        shaderProgram = glCreateProgram();
        glAttachShader(shaderProgram, vertexShader);
        glAttachShader(shaderProgram, fragmentShader);
        glLinkProgram(shaderProgram);
        
        success = glGetProgrami(shaderProgram, GL_LINK_STATUS);
        if (success == GL_FALSE) {
            int len = glGetProgrami(shaderProgram, GL_INFO_LOG_LENGTH);
            System.err.println("ERROR: Shader program linking failed!");
            System.err.println(glGetProgramInfoLog(shaderProgram, len));
            System.exit(-1);
        }
        
        glDeleteShader(vertexShader);
        glDeleteShader(fragmentShader);
    }
    
    private void setupVertexData() {
        // Cube vertices with positions and colors
        float[] vertices = OpenGLUtils.createCubeVertices();
        
        // Indices for drawing cube faces (using EBO)
        int[] indices = OpenGLUtils.createCubeIndices();
        
        VAO = glGenVertexArrays();
        VBO = glGenBuffers();
        EBO = glGenBuffers();
        
        glBindVertexArray(VAO);
        
        // Bind and set vertex buffer
        glBindBuffer(GL_ARRAY_BUFFER, VBO);
        FloatBuffer vertexBuffer = MemoryUtil.memAllocFloat(vertices.length);
        vertexBuffer.put(vertices).flip();
        glBufferData(GL_ARRAY_BUFFER, vertexBuffer, GL_STATIC_DRAW);
        MemoryUtil.memFree(vertexBuffer);
        
        // Bind and set index buffer
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, EBO);
        IntBuffer indexBuffer = MemoryUtil.memAllocInt(indices.length);
        indexBuffer.put(indices).flip();
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indexBuffer, GL_STATIC_DRAW);
        MemoryUtil.memFree(indexBuffer);
        
        // Position attribute
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 6 * Float.BYTES, 0);
        glEnableVertexAttribArray(0);
        
        // Color attribute
        glVertexAttribPointer(1, 3, GL_FLOAT, false, 6 * Float.BYTES, 3 * Float.BYTES);
        glEnableVertexAttribArray(1);
        
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindVertexArray(0);
    }
    
    private void loop() {
        glClearColor(0.1f, 0.1f, 0.1f, 1.0f);
        
        while (!glfwWindowShouldClose(window)) {
            glfwPollEvents();
            
            // Update rotation
            rotation += 0.02f;
            if (rotation > 2 * Math.PI) {
                rotation -= 2 * Math.PI;
            }
            
            // Clear buffers (both color and depth)
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            
            glUseProgram(shaderProgram);
            
            // Set rotation uniform
            int rotationLoc = glGetUniformLocation(shaderProgram, "rotation");
            glUniform1f(rotationLoc, rotation);
            
            glBindVertexArray(VAO);
            // Draw using indices (EBO)
            glDrawElements(GL_TRIANGLES, 36, GL_UNSIGNED_INT, 0);
            
            glfwSwapBuffers(window);
        }
    }
    
    private void cleanup() {
        glDeleteVertexArrays(VAO);
        glDeleteBuffers(VBO);
        glDeleteBuffers(EBO);
        glDeleteProgram(shaderProgram);
        
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }
    
    public static void main(String[] args) {
        new OpenGLCube().run();
    }
}
