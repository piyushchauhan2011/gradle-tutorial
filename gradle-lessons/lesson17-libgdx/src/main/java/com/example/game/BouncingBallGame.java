package com.example.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

/**
 * Bouncing Ball Game - A simple game demonstrating libGDX basics
 * 
 * Game mechanics:
 * - A ball bounces around the screen
 * - Player can control a paddle at the bottom
 * - Use arrow keys or A/D to move the paddle
 * - Try to keep the ball in play!
 */
public class BouncingBallGame extends ApplicationAdapter {
    
    private ShapeRenderer shapeRenderer;
    private Ball ball;
    private Paddle paddle;
    private GameState gameState;
    
    @Override
    public void create() {
        shapeRenderer = new ShapeRenderer();
        
        // Initialize game objects
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        
        ball = new Ball(screenWidth / 2, screenHeight / 2, 20);
        paddle = new Paddle(screenWidth / 2 - 50, 30, 100, 20);
        gameState = new GameState();
    }
    
    @Override
    public void render() {
        // Clear screen with dark blue color
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        // Handle input
        handleInput();
        
        // Update game state
        float deltaTime = Gdx.graphics.getDeltaTime();
        update(deltaTime);
        
        // Render game objects
        draw();
    }
    
    private void handleInput() {
        float paddleSpeed = 400f;
        float deltaTime = Gdx.graphics.getDeltaTime();
        
        // Move paddle left
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
            paddle.moveLeft(paddleSpeed * deltaTime);
        }
        
        // Move paddle right
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            paddle.moveRight(paddleSpeed * deltaTime);
        }
        
        // Reset game with R key
        if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
            resetGame();
        }
    }
    
    private void update(float deltaTime) {
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        
        // Update ball
        ball.update(deltaTime, screenWidth, screenHeight);
        
        // Check collision with paddle
        if (ball.getY() - ball.getRadius() <= paddle.getY() + paddle.getHeight() &&
            ball.getY() + ball.getRadius() >= paddle.getY() &&
            ball.getX() + ball.getRadius() >= paddle.getX() &&
            ball.getX() - ball.getRadius() <= paddle.getX() + paddle.getWidth()) {
            
            // Ball hit paddle - bounce up
            ball.bounceY();
            gameState.incrementScore();
        }
        
        // Check if ball fell off bottom
        if (ball.getY() + ball.getRadius() < 0) {
            gameState.incrementLives();
            if (gameState.getLives() <= 0) {
                // Game over - reset
                resetGame();
            } else {
                // Reset ball position
                ball.reset(screenWidth / 2, screenHeight / 2);
            }
        }
        
        // Keep paddle on screen
        paddle.keepOnScreen(screenWidth);
    }
    
    private void draw() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        
        // Draw ball
        shapeRenderer.setColor(1, 0.5f, 0, 1); // Orange
        shapeRenderer.circle(ball.getX(), ball.getY(), ball.getRadius());
        
        // Draw paddle
        shapeRenderer.setColor(0, 1, 0, 1); // Green
        shapeRenderer.rect(paddle.getX(), paddle.getY(), paddle.getWidth(), paddle.getHeight());
        
        shapeRenderer.end();
        
        // Draw UI text (using simple shape renderer for demo)
        // In a real game, you'd use SpriteBatch and BitmapFont
        drawScore();
    }
    
    private void drawScore() {
        // Simple visual indicator for score and lives
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1, 1, 1, 0.3f);
        
        // Draw score indicator (small squares)
        float x = 20;
        float y = Gdx.graphics.getHeight() - 30;
        for (int i = 0; i < gameState.getScore(); i++) {
            shapeRenderer.rect(x + i * 15, y, 10, 10);
        }
        
        // Draw lives indicator
        x = Gdx.graphics.getWidth() - 120;
        shapeRenderer.setColor(1, 0, 0, 0.5f);
        for (int i = 0; i < gameState.getLives(); i++) {
            shapeRenderer.circle(x + i * 20, y + 5, 5);
        }
        
        shapeRenderer.end();
    }
    
    private void resetGame() {
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        
        ball.reset(screenWidth / 2, screenHeight / 2);
        paddle.reset(screenWidth / 2 - 50);
        gameState.reset();
    }
    
    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }
}
