/**
 * Utility class for OpenGL operations
 * This class contains testable helper methods for OpenGL operations
 */
public class OpenGLUtils {
    
    /**
     * Validates vertex data format
     * @param vertices Array of vertex data
     * @param floatsPerVertex Expected number of floats per vertex
     * @return true if vertex data is valid
     */
    public static boolean validateVertexData(float[] vertices, int floatsPerVertex) {
        if (vertices == null || vertices.length == 0) {
            return false;
        }
        return vertices.length % floatsPerVertex == 0;
    }
    
    /**
     * Calculates the number of vertices from vertex data
     * @param vertices Array of vertex data
     * @param floatsPerVertex Number of floats per vertex
     * @return Number of vertices
     */
    public static int getVertexCount(float[] vertices, int floatsPerVertex) {
        if (vertices == null || vertices.length == 0) {
            return 0;
        }
        return vertices.length / floatsPerVertex;
    }
    
    /**
     * Validates shader source code syntax (basic checks)
     * @param shaderSource Shader source code
     * @return true if shader source appears valid
     */
    public static boolean validateShaderSource(String shaderSource) {
        if (shaderSource == null || shaderSource.trim().isEmpty()) {
            return false;
        }
        
        // Check for version directive
        if (!shaderSource.contains("#version")) {
            return false;
        }
        
        // Check for main function
        if (!shaderSource.contains("void main()")) {
            return false;
        }
        
        return true;
    }
    
    /**
     * Validates index data for indexed drawing
     * @param indices Array of indices
     * @param maxVertexIndex Maximum valid vertex index
     * @return true if indices are valid
     */
    public static boolean validateIndices(int[] indices, int maxVertexIndex) {
        if (indices == null || indices.length == 0) {
            return false;
        }
        
        for (int index : indices) {
            if (index < 0 || index > maxVertexIndex) {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Calculates the number of triangles from vertex count
     * @param vertexCount Number of vertices
     * @return Number of triangles
     */
    public static int getTriangleCount(int vertexCount) {
        if (vertexCount < 3) {
            return 0;
        }
        return vertexCount / 3;
    }
    
    /**
     * Calculates the number of triangles from index count
     * @param indexCount Number of indices
     * @return Number of triangles
     */
    public static int getTriangleCountFromIndices(int indexCount) {
        if (indexCount < 3) {
            return 0;
        }
        return indexCount / 3;
    }
    
    /**
     * Validates color values (RGB in range 0.0-1.0)
     * @param r Red component
     * @param g Green component
     * @param b Blue component
     * @return true if color values are valid
     */
    public static boolean validateColor(float r, float g, float b) {
        return r >= 0.0f && r <= 1.0f &&
               g >= 0.0f && g <= 1.0f &&
               b >= 0.0f && b <= 1.0f;
    }
    
    /**
     * Normalizes color values to 0.0-1.0 range
     * @param r Red component (0-255)
     * @param g Green component (0-255)
     * @param b Blue component (0-255)
     * @return Array of normalized color values [r, g, b]
     */
    public static float[] normalizeColor(int r, int g, int b) {
        return new float[]{
            Math.max(0.0f, Math.min(1.0f, r / 255.0f)),
            Math.max(0.0f, Math.min(1.0f, g / 255.0f)),
            Math.max(0.0f, Math.min(1.0f, b / 255.0f))
        };
    }
    
    /**
     * Creates triangle vertex data
     * @param x1, y1, z1 First vertex position
     * @param r1, g1, b1 First vertex color
     * @param x2, y2, z2 Second vertex position
     * @param r2, g2, b2 Second vertex color
     * @param x3, y3, z3 Third vertex position
     * @param r3, g3, b3 Third vertex color
     * @return Array of vertex data (position + color for each vertex)
     */
    public static float[] createTriangleVertices(
            float x1, float y1, float z1, float r1, float g1, float b1,
            float x2, float y2, float z2, float r2, float g2, float b2,
            float x3, float y3, float z3, float r3, float g3, float b3) {
        return new float[]{
            x1, y1, z1, r1, g1, b1,
            x2, y2, z2, r2, g2, b2,
            x3, y3, z3, r3, g3, b3
        };
    }
    
    /**
     * Creates cube vertex data (8 vertices with positions and colors)
     * @return Array of vertex data
     */
    public static float[] createCubeVertices() {
        return new float[]{
            // Front face (z = 0.5)
            -0.5f, -0.5f,  0.5f,  1.0f, 0.0f, 0.0f, // Red
             0.5f, -0.5f,  0.5f,  0.0f, 1.0f, 0.0f, // Green
             0.5f,  0.5f,  0.5f,  0.0f, 0.0f, 1.0f, // Blue
            -0.5f,  0.5f,  0.5f,  1.0f, 1.0f, 0.0f, // Yellow
            
            // Back face (z = -0.5)
            -0.5f, -0.5f, -0.5f,  1.0f, 0.0f, 1.0f, // Magenta
             0.5f, -0.5f, -0.5f,  0.0f, 1.0f, 1.0f, // Cyan
             0.5f,  0.5f, -0.5f,  1.0f, 1.0f, 1.0f, // White
            -0.5f,  0.5f, -0.5f,  0.5f, 0.5f, 0.5f  // Gray
        };
    }
    
    /**
     * Creates cube indices for drawing cube faces
     * @return Array of indices
     */
    public static int[] createCubeIndices() {
        return new int[]{
            // Front face
            0, 1, 2,  2, 3, 0,
            // Back face
            4, 5, 6,  6, 7, 4,
            // Left face
            0, 3, 7,  7, 4, 0,
            // Right face
            1, 2, 6,  6, 5, 1,
            // Top face
            3, 2, 6,  6, 7, 3,
            // Bottom face
            0, 1, 5,  5, 4, 0
        };
    }
}
