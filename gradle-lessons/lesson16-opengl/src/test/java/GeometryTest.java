import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for geometry calculations and vertex data
 */
public class GeometryTest {
    
    @Test
    public void testTriangleGeometry() {
        float[] triangleVertices = OpenGLUtils.createTriangleVertices(
            -0.5f, -0.5f, 0.0f, 1.0f, 0.0f, 0.0f, // Bottom left - Red
             0.5f, -0.5f, 0.0f, 0.0f, 1.0f, 0.0f, // Bottom right - Green
             0.0f,  0.5f, 0.0f, 0.0f, 0.0f, 1.0f  // Top - Blue
        );
        
        // Validate structure
        assertTrue("Triangle vertices should be valid", 
                   OpenGLUtils.validateVertexData(triangleVertices, 6));
        
        int vertexCount = OpenGLUtils.getVertexCount(triangleVertices, 6);
        assertEquals("Triangle should have 3 vertices", 3, vertexCount);
        
        int triangleCount = OpenGLUtils.getTriangleCount(vertexCount);
        assertEquals("Should have 1 triangle", 1, triangleCount);
    }
    
    @Test
    public void testCubeGeometry() {
        float[] cubeVertices = OpenGLUtils.createCubeVertices();
        int[] cubeIndices = OpenGLUtils.createCubeIndices();
        
        // Validate vertices
        assertTrue("Cube vertices should be valid", 
                   OpenGLUtils.validateVertexData(cubeVertices, 6));
        
        int vertexCount = OpenGLUtils.getVertexCount(cubeVertices, 6);
        assertEquals("Cube should have 8 vertices", 8, vertexCount);
        
        // Validate indices
        assertTrue("Cube indices should be valid", 
                   OpenGLUtils.validateIndices(cubeIndices, vertexCount - 1));
        
        int triangleCount = OpenGLUtils.getTriangleCountFromIndices(cubeIndices.length);
        assertEquals("Cube should have 12 triangles (6 faces * 2)", 12, triangleCount);
    }
    
    @Test
    public void testVertexDataFormat() {
        // Test that vertex data follows format: x, y, z, r, g, b
        float[] vertices = OpenGLUtils.createTriangleVertices(
            1.0f, 2.0f, 3.0f, 0.1f, 0.2f, 0.3f,
            4.0f, 5.0f, 6.0f, 0.4f, 0.5f, 0.6f,
            7.0f, 8.0f, 9.0f, 0.7f, 0.8f, 0.9f
        );
        
        // Verify first vertex structure
        assertEquals("First vertex X", 1.0f, vertices[0], 0.001f);
        assertEquals("First vertex Y", 2.0f, vertices[1], 0.001f);
        assertEquals("First vertex Z", 3.0f, vertices[2], 0.001f);
        assertEquals("First vertex R", 0.1f, vertices[3], 0.001f);
        assertEquals("First vertex G", 0.2f, vertices[4], 0.001f);
        assertEquals("First vertex B", 0.3f, vertices[5], 0.001f);
    }
    
    @Test
    public void testColorValidation() {
        // Test triangle colors
        assertTrue("Red color should be valid", 
                   OpenGLUtils.validateColor(1.0f, 0.0f, 0.0f));
        assertTrue("Green color should be valid", 
                   OpenGLUtils.validateColor(0.0f, 1.0f, 0.0f));
        assertTrue("Blue color should be valid", 
                   OpenGLUtils.validateColor(0.0f, 0.0f, 1.0f));
    }
    
    @Test
    public void testIndexReuse() {
        // Cube uses indices to reuse vertices
        int[] indices = OpenGLUtils.createCubeIndices();
        float[] vertices = OpenGLUtils.createCubeVertices();
        
        // Cube has 8 vertices but 36 indices (reusing vertices)
        int vertexCount = OpenGLUtils.getVertexCount(vertices, 6);
        assertEquals("Cube has 8 vertices", 8, vertexCount);
        
        // Each vertex should be used in multiple triangles
        // Count unique indices
        java.util.Set<Integer> uniqueIndices = new java.util.HashSet<>();
        for (int index : indices) {
            uniqueIndices.add(index);
        }
        
        // All 8 vertices should be used
        assertEquals("All 8 vertices should be referenced", 
                     vertexCount, uniqueIndices.size());
    }
}
