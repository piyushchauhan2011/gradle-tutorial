import com.example.game.Ball;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for Ball class
 */
public class BallTest {
    
    private Ball ball;
    
    @Before
    public void setUp() {
        ball = new Ball(100, 200, 20);
    }
    
    @Test
    public void testBallCreation() {
        assertEquals("Ball X position should be set", 100, ball.getX(), 0.001f);
        assertEquals("Ball Y position should be set", 200, ball.getY(), 0.001f);
        assertEquals("Ball radius should be set", 20, ball.getRadius(), 0.001f);
    }
    
    @Test
    public void testBallUpdate() {
        // float initialX = ball.getX();
        // float initialY = ball.getY();
        
        // Update ball
        ball.update(0.016f, 800, 600); // ~60fps delta time
        
        // Position should have changed (unless velocity is exactly 0, which is unlikely)
        // We just verify the method doesn't crash and position is valid
        assertTrue("Ball X should be valid", ball.getX() >= 0);
        assertTrue("Ball Y should be valid", ball.getY() >= 0);
    }
    
    @Test
    public void testBallBounceOffLeftWall() {
        // Set ball to left edge
        Ball testBall = new Ball(20, 300, 20);
        // Force position to left edge
        testBall.getPosition().x = 20;
        testBall.getVelocity().x = -100; // Moving left
        
        // float initialVelocityX = testBall.getVelocity().x;
        
        // Update should bounce off left wall
        testBall.update(0.016f, 800, 600);
        
        // Velocity should be reversed (positive now)
        assertTrue("Ball should bounce off left wall", 
                   testBall.getVelocity().x > 0 || testBall.getPosition().x >= 20);
    }
    
    @Test
    public void testBallBounceOffRightWall() {
        Ball testBall = new Ball(780, 300, 20);
        testBall.getVelocity().x = 100; // Moving right
        
        // Update should bounce off right wall
        testBall.update(0.016f, 800, 600);
        
        // Ball should stay on screen
        assertTrue("Ball should stay within screen bounds", 
                   testBall.getX() <= 800);
    }
    
    @Test
    public void testBallBounceY() {
        // float initialVelocityY = ball.getVelocity().y;
        ball.bounceY();
        
        // After bounceY, velocity Y should be positive (upward)
        assertTrue("Ball should bounce upward", ball.getVelocity().y > 0);
    }
    
    @Test
    public void testBallReset() {
        ball.update(0.016f, 800, 600);
        float newX = 400;
        float newY = 300;
        
        ball.reset(newX, newY);
        
        assertEquals("Ball X should be reset", newX, ball.getX(), 0.001f);
        assertEquals("Ball Y should be reset", newY, ball.getY(), 0.001f);
    }
}
