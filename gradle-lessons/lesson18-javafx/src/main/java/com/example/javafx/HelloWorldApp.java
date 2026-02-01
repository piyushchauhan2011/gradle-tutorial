package com.example.javafx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Basic JavaFX Hello World Application
 * 
 * Demonstrates:
 * - Basic JavaFX application structure
 * - Stage and Scene creation
 * - Layout containers (VBox)
 * - Controls (Label, Button)
 * - Event handling
 */
public class HelloWorldApp extends Application {
    
    private int clickCount = 0;
    private Label messageLabel;
    
    @Override
    public void start(Stage primaryStage) {
        // Set window title
        primaryStage.setTitle("JavaFX Hello World - Lesson 18");
        
        // Create a label
        messageLabel = new Label("Hello, JavaFX!");
        messageLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        
        // Create a button
        Button clickButton = new Button("Click Me!");
        clickButton.setStyle("-fx-font-size: 14px; -fx-padding: 10px;");
        
        // Set button action
        clickButton.setOnAction(e -> {
            clickCount++;
            messageLabel.setText("Button clicked " + clickCount + " time(s)!");
        });
        
        // Create a layout container (VBox - vertical box)
        VBox root = new VBox(20); // 20px spacing between children
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(30));
        root.getChildren().addAll(messageLabel, clickButton);
        
        // Create the scene
        Scene scene = new Scene(root, 400, 300);
        
        // Set the scene on the stage
        primaryStage.setScene(scene);
        
        // Show the window
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
