package com.example.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

/**
 * Desktop launcher for the game
 * This class initializes the libGDX application for desktop platforms
 */
public class DesktopLauncher {
    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        
        // Configure window settings
        config.setTitle("libGDX Game - Lesson 17");
        config.setWindowedMode(800, 600);
        config.setResizable(false);
        config.useVsync(true);
        config.setForegroundFPS(60);
        
        // Create and launch the application
        new Lwjgl3Application(new BouncingBallGame(), config);
    }
}
