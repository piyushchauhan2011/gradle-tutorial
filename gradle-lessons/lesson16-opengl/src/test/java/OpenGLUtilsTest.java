import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for OpenGL utility functions
 */
public class OpenGLUtilsTest {
    
    @Test
    public void testValidateVertexData_ValidData() {
        float[] vertices = {0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f};
        assertTrue("Valid vertex data should pass validation", 
                   OpenGLUtils.validateVertexData(vertices, 6));
    }
    
    @Test
    public void testValidateVertexData_InvalidData() {
        float[] vertices = {0.0f, 0.0f, 0.0f, 1.0f}; // Only 4 floats, not divisible by 6
        assertFalse("Invalid vertex data should fail validation", 
                    OpenGLUtils.validateVertexData(vertices, 6));
    }
    
    @Test
    public void testValidateVertexData_NullData() {
        assertFalse("Null vertex data should fail validation", 
                    OpenGLUtils.validateVertexData(null, 6));
    }
    
    @Test
    public void testValidateVertexData_EmptyData() {
        float[] vertices = {};
        assertFalse("Empty vertex data should fail validation", 
                    OpenGLUtils.validateVertexData(vertices, 6));
    }
    
    @Test
    public void testGetVertexCount() {
        float[] vertices = {
            0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f,
            1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f,
            0.5f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f
        };
        assertEquals("Should calculate 3 vertices correctly", 
                     3, OpenGLUtils.getVertexCount(vertices, 6));
    }
    
    @Test
    public void testGetVertexCount_Empty() {
        float[] vertices = {};
        assertEquals("Empty array should return 0 vertices", 
                     0, OpenGLUtils.getVertexCount(vertices, 6));
    }
    
    @Test
    public void testValidateShaderSource_ValidVertexShader() {
        String shader = "#version 330 core\n" +
                       "layout (location = 0) in vec3 aPos;\n" +
                       "void main() {\n" +
                       "   gl_Position = vec4(aPos, 1.0);\n" +
                       "}\n";
        assertTrue("Valid vertex shader should pass validation", 
                   OpenGLUtils.validateShaderSource(shader));
    }
    
    @Test
    public void testValidateShaderSource_ValidFragmentShader() {
        String shader = "#version 330 core\n" +
                       "out vec4 FragColor;\n" +
                       "void main() {\n" +
                       "   FragColor = vec4(1.0, 0.0, 0.0, 1.0);\n" +
                       "}\n";
        assertTrue("Valid fragment shader should pass validation", 
                   OpenGLUtils.validateShaderSource(shader));
    }
    
    @Test
    public void testValidateShaderSource_MissingVersion() {
        String shader = "void main() { gl_Position = vec4(0.0); }";
        assertFalse("Shader without version directive should fail", 
                    OpenGLUtils.validateShaderSource(shader));
    }
    
    @Test
    public void testValidateShaderSource_MissingMain() {
        String shader = "#version 330 core\nfloat x = 1.0;";
        assertFalse("Shader without main function should fail", 
                    OpenGLUtils.validateShaderSource(shader));
    }
    
    @Test
    public void testValidateShaderSource_Null() {
        assertFalse("Null shader source should fail", 
                    OpenGLUtils.validateShaderSource(null));
    }
    
    @Test
    public void testValidateShaderSource_Empty() {
        assertFalse("Empty shader source should fail", 
                    OpenGLUtils.validateShaderSource(""));
    }
    
    @Test
    public void testValidateIndices_Valid() {
        int[] indices = {0, 1, 2, 2, 3, 0};
        assertTrue("Valid indices should pass validation", 
                   OpenGLUtils.validateIndices(indices, 3));
    }
    
    @Test
    public void testValidateIndices_OutOfRange() {
        int[] indices = {0, 1, 5}; // 5 is out of range for max index 3
        assertFalse("Out of range indices should fail validation", 
                    OpenGLUtils.validateIndices(indices, 3));
    }
    
    @Test
    public void testValidateIndices_Negative() {
        int[] indices = {0, -1, 2};
        assertFalse("Negative indices should fail validation", 
                    OpenGLUtils.validateIndices(indices, 3));
    }
    
    @Test
    public void testValidateIndices_Null() {
        assertFalse("Null indices should fail validation", 
                    OpenGLUtils.validateIndices(null, 3));
    }
    
    @Test
    public void testGetTriangleCount() {
        assertEquals("3 vertices should make 1 triangle", 
                     1, OpenGLUtils.getTriangleCount(3));
        assertEquals("6 vertices should make 2 triangles", 
                     2, OpenGLUtils.getTriangleCount(6));
        assertEquals("9 vertices should make 3 triangles", 
                     3, OpenGLUtils.getTriangleCount(9));
    }
    
    @Test
    public void testGetTriangleCount_InsufficientVertices() {
        assertEquals("Less than 3 vertices should make 0 triangles", 
                     0, OpenGLUtils.getTriangleCount(2));
        assertEquals("0 vertices should make 0 triangles", 
                     0, OpenGLUtils.getTriangleCount(0));
    }
    
    @Test
    public void testGetTriangleCountFromIndices() {
        assertEquals("3 indices should make 1 triangle", 
                     1, OpenGLUtils.getTriangleCountFromIndices(3));
        assertEquals("6 indices should make 2 triangles", 
                     2, OpenGLUtils.getTriangleCountFromIndices(6));
    }
    
    @Test
    public void testValidateColor_Valid() {
        assertTrue("Valid color should pass", 
                   OpenGLUtils.validateColor(1.0f, 0.5f, 0.0f));
        assertTrue("Valid color at boundaries should pass", 
                   OpenGLUtils.validateColor(0.0f, 1.0f, 0.5f));
    }
    
    @Test
    public void testValidateColor_Invalid() {
        assertFalse("Color above 1.0 should fail", 
                    OpenGLUtils.validateColor(1.5f, 0.5f, 0.0f));
        assertFalse("Negative color should fail", 
                    OpenGLUtils.validateColor(-0.1f, 0.5f, 0.0f));
    }
    
    @Test
    public void testNormalizeColor() {
        float[] normalized = OpenGLUtils.normalizeColor(255, 128, 0);
        assertEquals("Red should normalize to 1.0", 1.0f, normalized[0], 0.001f);
        assertEquals("Green should normalize to ~0.5", 0.502f, normalized[1], 0.001f);
        assertEquals("Blue should normalize to 0.0", 0.0f, normalized[2], 0.001f);
    }
    
    @Test
    public void testNormalizeColor_ClampsValues() {
        float[] normalized = OpenGLUtils.normalizeColor(300, -10, 0);
        assertEquals("Values above 255 should clamp to 1.0", 1.0f, normalized[0], 0.001f);
        assertEquals("Negative values should clamp to 0.0", 0.0f, normalized[1], 0.001f);
    }
    
    @Test
    public void testCreateTriangleVertices() {
        float[] vertices = OpenGLUtils.createTriangleVertices(
            -0.5f, -0.5f, 0.0f, 1.0f, 0.0f, 0.0f,
             0.5f, -0.5f, 0.0f, 0.0f, 1.0f, 0.0f,
             0.0f,  0.5f, 0.0f, 0.0f, 0.0f, 1.0f
        );
        
        assertEquals("Should create 18 floats (3 vertices * 6 floats)", 
                     18, vertices.length);
        assertEquals("First vertex X position", -0.5f, vertices[0], 0.001f);
        assertEquals("First vertex red component", 1.0f, vertices[3], 0.001f);
        assertEquals("Second vertex green component", 1.0f, vertices[10], 0.001f);
    }
    
    @Test
    public void testCreateCubeVertices() {
        float[] vertices = OpenGLUtils.createCubeVertices();
        
        assertEquals("Cube should have 8 vertices * 6 floats = 48 floats", 
                     48, vertices.length);
        
        // Verify first vertex (front bottom left)
        assertEquals("First vertex X", -0.5f, vertices[0], 0.001f);
        assertEquals("First vertex Y", -0.5f, vertices[1], 0.001f);
        assertEquals("First vertex Z", 0.5f, vertices[2], 0.001f);
    }
    
    @Test
    public void testCreateCubeIndices() {
        int[] indices = OpenGLUtils.createCubeIndices();
        
        assertEquals("Cube should have 6 faces * 2 triangles * 3 indices = 36 indices", 
                     36, indices.length);
        
        // Verify first triangle (front face)
        assertEquals("First index", 0, indices[0]);
        assertEquals("Second index", 1, indices[1]);
        assertEquals("Third index", 2, indices[2]);
    }
    
    @Test
    public void testCreateCubeIndices_ValidRange() {
        int[] indices = OpenGLUtils.createCubeIndices();
        
        // All indices should be valid (0-7 for 8 vertices)
        assertTrue("All cube indices should be valid", 
                   OpenGLUtils.validateIndices(indices, 7));
    }
}
