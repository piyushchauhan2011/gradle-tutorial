package com.example.game;

/**
 * Manages game state (score, lives, etc.)
 */
public class GameState {
    private int score;
    private int lives;
    private static final int INITIAL_LIVES = 3;
    
    public GameState() {
        reset();
    }
    
    public void reset() {
        score = 0;
        lives = INITIAL_LIVES;
    }
    
    public void incrementScore() {
        score++;
    }
    
    public void incrementLives() {
        lives--;
    }
    
    public int getScore() {
        return score;
    }
    
    public int getLives() {
        return lives;
    }
    
    public boolean isGameOver() {
        return lives <= 0;
    }
}
