package com.example.game;

/**
 * Represents the player-controlled paddle
 */
public class Paddle {
    private float x;
    private float y;
    private float width;
    private float height;
    
    public Paddle(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public void moveLeft(float distance) {
        x -= distance;
    }
    
    public void moveRight(float distance) {
        x += distance;
    }
    
    public void keepOnScreen(float screenWidth) {
        if (x < 0) {
            x = 0;
        }
        if (x + width > screenWidth) {
            x = screenWidth - width;
        }
    }
    
    public void reset(float x) {
        this.x = x;
    }
    
    public float getX() {
        return x;
    }
    
    public float getY() {
        return y;
    }
    
    public float getWidth() {
        return width;
    }
    
    public float getHeight() {
        return height;
    }
}
