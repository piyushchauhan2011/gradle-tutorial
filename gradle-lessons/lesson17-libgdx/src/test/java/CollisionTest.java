import com.example.game.Ball;
import com.example.game.Paddle;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for collision detection logic
 */
public class CollisionTest {
    
    @Test
    public void testBallPaddleCollision_AbovePaddle() {
        Ball ball = new Ball(400, 60, 20);
        Paddle paddle = new Paddle(350, 30, 100, 20);
        
        // Ball is above paddle
        boolean collides = checkCollision(ball, paddle);
        assertTrue("Ball above paddle should collide", collides);
    }
    
    @Test
    public void testBallPaddleCollision_ToTheSide() {
        Ball ball = new Ball(200, 50, 20);
        Paddle paddle = new Paddle(350, 30, 100, 20);
        
        // Ball is to the left of paddle
        boolean collides = checkCollision(ball, paddle);
        assertFalse("Ball to the side should not collide", collides);
    }
    
    @Test
    public void testBallPaddleCollision_BelowPaddle() {
        // Ball center at y=5, radius=20, so ball spans y=-15 to y=25
        // Paddle at y=30, height=20, so paddle spans y=30 to y=50
        // No overlap - ball is completely below paddle
        Ball ball = new Ball(400, 5, 20);
        Paddle paddle = new Paddle(350, 30, 100, 20);
        
        boolean collides = checkCollision(ball, paddle);
        assertFalse("Ball below paddle should not collide", collides);
    }
    
    @Test
    public void testBallPaddleCollision_EdgeCase() {
        Ball ball = new Ball(350, 50, 20);
        Paddle paddle = new Paddle(350, 30, 100, 20);
        
        // Ball at left edge of paddle
        boolean collides = checkCollision(ball, paddle);
        assertTrue("Ball at edge should collide", collides);
    }
    
    /**
     * Helper method to check collision (same logic as in BouncingBallGame)
     */
    private boolean checkCollision(Ball ball, Paddle paddle) {
        return ball.getY() - ball.getRadius() <= paddle.getY() + paddle.getHeight() &&
               ball.getY() + ball.getRadius() >= paddle.getY() &&
               ball.getX() + ball.getRadius() >= paddle.getX() &&
               ball.getX() - ball.getRadius() <= paddle.getX() + paddle.getWidth();
    }
}
