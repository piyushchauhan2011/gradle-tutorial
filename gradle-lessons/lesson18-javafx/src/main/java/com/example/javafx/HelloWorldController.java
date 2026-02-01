package com.example.javafx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Controller for HelloWorld.fxml
 * 
 * Demonstrates:
 * - FXML controller pattern
 * - @FXML annotation for injection
 * - Event handler methods
 */
public class HelloWorldController {
    
    @FXML
    private Label messageLabel;
    
    @FXML
    private Button clickButton;
    
    private int clickCount = 0;
    
    /**
     * Called after FXML loading is complete
     * Use this to initialize any additional setup
     */
    @FXML
    private void initialize() {
        // Initialization code if needed
        messageLabel.setText("Hello, JavaFX!");
    }
    
    /**
     * Event handler for button click
     * Called from FXML: onAction="#handleButtonClick"
     */
    @FXML
    private void handleButtonClick() {
        clickCount++;
        messageLabel.setText("Button clicked " + clickCount + " time(s)!");
    }
}
