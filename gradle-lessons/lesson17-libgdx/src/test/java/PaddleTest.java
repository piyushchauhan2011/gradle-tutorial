import com.example.game.Paddle;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for Paddle class
 */
public class PaddleTest {
    
    private Paddle paddle;
    
    @Before
    public void setUp() {
        paddle = new Paddle(100, 30, 100, 20);
    }
    
    @Test
    public void testPaddleCreation() {
        assertEquals("Paddle X should be set", 100, paddle.getX(), 0.001f);
        assertEquals("Paddle Y should be set", 30, paddle.getY(), 0.001f);
        assertEquals("Paddle width should be set", 100, paddle.getWidth(), 0.001f);
        assertEquals("Paddle height should be set", 20, paddle.getHeight(), 0.001f);
    }
    
    @Test
    public void testMoveLeft() {
        float initialX = paddle.getX();
        paddle.moveLeft(50);
        
        assertEquals("Paddle should move left", initialX - 50, paddle.getX(), 0.001f);
    }
    
    @Test
    public void testMoveRight() {
        float initialX = paddle.getX();
        paddle.moveRight(50);
        
        assertEquals("Paddle should move right", initialX + 50, paddle.getX(), 0.001f);
    }
    
    @Test
    public void testKeepOnScreen_LeftBoundary() {
        paddle = new Paddle(-10, 30, 100, 20);
        paddle.keepOnScreen(800);
        
        assertEquals("Paddle should not go off left edge", 0, paddle.getX(), 0.001f);
    }
    
    @Test
    public void testKeepOnScreen_RightBoundary() {
        paddle = new Paddle(750, 30, 100, 20);
        paddle.keepOnScreen(800);
        
        assertEquals("Paddle should not go off right edge", 700, paddle.getX(), 0.001f);
    }
    
    @Test
    public void testKeepOnScreen_WithinBounds() {
        paddle = new Paddle(300, 30, 100, 20);
        float initialX = paddle.getX();
        paddle.keepOnScreen(800);
        
        assertEquals("Paddle should stay in same position if within bounds", 
                     initialX, paddle.getX(), 0.001f);
    }
    
    @Test
    public void testReset() {
        paddle.moveRight(50);
        paddle.reset(200);
        
        assertEquals("Paddle should reset to new X position", 200, paddle.getX(), 0.001f);
    }
}
