import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for shader source code validation
 */
public class ShaderSourceTest {
    
    @Test
    public void testTriangleVertexShaderSource() {
        String vertexShader = "#version 330 core\n" +
            "layout (location = 0) in vec3 aPos;\n" +
            "layout (location = 1) in vec3 aColor;\n" +
            "out vec3 vertexColor;\n" +
            "void main() {\n" +
            "   gl_Position = vec4(aPos.x, aPos.y, aPos.z, 1.0);\n" +
            "   vertexColor = aColor;\n" +
            "}\n";
        
        assertTrue("Triangle vertex shader should be valid", 
                   OpenGLUtils.validateShaderSource(vertexShader));
        assertTrue("Should contain version directive", 
                   vertexShader.contains("#version"));
        assertTrue("Should contain main function", 
                   vertexShader.contains("void main()"));
    }
    
    @Test
    public void testTriangleFragmentShaderSource() {
        String fragmentShader = "#version 330 core\n" +
            "in vec3 vertexColor;\n" +
            "out vec4 FragColor;\n" +
            "void main() {\n" +
            "   FragColor = vec4(vertexColor, 1.0f);\n" +
            "}\n";
        
        assertTrue("Triangle fragment shader should be valid", 
                   OpenGLUtils.validateShaderSource(fragmentShader));
    }
    
    @Test
    public void testCubeVertexShaderSource() {
        String vertexShader = "#version 330 core\n" +
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
        
        assertTrue("Cube vertex shader should be valid", 
                   OpenGLUtils.validateShaderSource(vertexShader));
        assertTrue("Should contain uniform declaration", 
                   vertexShader.contains("uniform"));
    }
}
