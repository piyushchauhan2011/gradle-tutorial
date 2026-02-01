package com.example.game;

import com.badlogic.gdx.math.Vector2;

/**
 * Represents a bouncing ball in the game
 */
public class Ball {
    private Vector2 position;
    private Vector2 velocity;
    private float radius;
    
    public Ball(float x, float y, float radius) {
        this.position = new Vector2(x, y);
        this.radius = radius;
        // Start with random velocity
        this.velocity = new Vector2(
            (float)(Math.random() * 200 - 100),
            (float)(Math.random() * 200 + 100)
        );
    }
    
    public void update(float deltaTime, float screenWidth, float screenHeight) {
        // Update position based on velocity
        position.x += velocity.x * deltaTime;
        position.y += velocity.y * deltaTime;
        
        // Bounce off walls
        if (position.x - radius <= 0 || position.x + radius >= screenWidth) {
            velocity.x = -velocity.x;
            // Keep ball on screen
            if (position.x - radius <= 0) {
                position.x = radius;
            } else {
                position.x = screenWidth - radius;
            }
        }
        
        if (position.y + radius >= screenHeight) {
            velocity.y = -velocity.y;
            position.y = screenHeight - radius;
        }
    }
    
    public void bounceY() {
        velocity.y = Math.abs(velocity.y); // Ensure ball goes up
        // Add some randomness to make it interesting
        velocity.x += (Math.random() * 40 - 20);
        // Limit max speed
        if (velocity.x > 300) velocity.x = 300;
        if (velocity.x < -300) velocity.x = -300;
    }
    
    public void reset(float x, float y) {
        position.set(x, y);
        velocity.set(
            (float)(Math.random() * 200 - 100),
            (float)(Math.random() * 200 + 100)
        );
    }
    
    public float getX() {
        return position.x;
    }
    
    public float getY() {
        return position.y;
    }
    
    public float getRadius() {
        return radius;
    }
    
    public Vector2 getPosition() {
        return position;
    }
    
    public Vector2 getVelocity() {
        return velocity;
    }
}
