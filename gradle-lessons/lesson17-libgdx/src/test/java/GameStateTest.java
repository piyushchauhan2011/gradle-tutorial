import com.example.game.GameState;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for GameState class
 */
public class GameStateTest {
    
    private GameState gameState;
    
    @Before
    public void setUp() {
        gameState = new GameState();
    }
    
    @Test
    public void testInitialState() {
        assertEquals("Initial score should be 0", 0, gameState.getScore());
        assertEquals("Initial lives should be 3", 3, gameState.getLives());
        assertFalse("Game should not be over initially", gameState.isGameOver());
    }
    
    @Test
    public void testIncrementScore() {
        gameState.incrementScore();
        assertEquals("Score should increment", 1, gameState.getScore());
        
        gameState.incrementScore();
        gameState.incrementScore();
        assertEquals("Score should increment multiple times", 3, gameState.getScore());
    }
    
    @Test
    public void testIncrementLives() {
        gameState.incrementLives();
        assertEquals("Lives should decrement", 2, gameState.getLives());
        
        gameState.incrementLives();
        assertEquals("Lives should decrement again", 1, gameState.getLives());
    }
    
    @Test
    public void testGameOver() {
        assertFalse("Game should not be over with 3 lives", gameState.isGameOver());
        
        gameState.incrementLives();
        assertFalse("Game should not be over with 2 lives", gameState.isGameOver());
        
        gameState.incrementLives();
        assertFalse("Game should not be over with 1 life", gameState.isGameOver());
        
        gameState.incrementLives();
        assertTrue("Game should be over with 0 lives", gameState.isGameOver());
    }
    
    @Test
    public void testReset() {
        // Modify state
        gameState.incrementScore();
        gameState.incrementScore();
        gameState.incrementLives();
        
        // Reset
        gameState.reset();
        
        assertEquals("Score should reset to 0", 0, gameState.getScore());
        assertEquals("Lives should reset to 3", 3, gameState.getLives());
        assertFalse("Game should not be over after reset", gameState.isGameOver());
    }
}
