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
 * HelloWorld Application with External CSS
 * 
 * Demonstrates:
 * - External CSS stylesheet loading
 * - CSS class selectors
 * - CSS ID selectors
 * - CSS pseudo-classes (hover, pressed)
 * - Separating style from code
 */
public class HelloWorldCssApp extends Application {
    
    private int clickCount = 0;
    private Label messageLabel;
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("JavaFX Hello World (CSS) - Lesson 18");
        
        // Create UI elements
        messageLabel = new Label("Hello, JavaFX!");
        messageLabel.setId("messageLabel");
        messageLabel.getStyleClass().add("message-label");
        
        Button clickButton = new Button("Click Me!");
        clickButton.setId("clickButton");
        clickButton.getStyleClass().add("primary-button");
        
        // Set button action
        clickButton.setOnAction(e -> {
            clickCount++;
            messageLabel.setText("Button clicked " + clickCount + " time(s)!");
        });
        
        // Create layout
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(30));
        root.getStyleClass().add("container");
        root.getChildren().addAll(messageLabel, clickButton);
        
        // Create scene
        Scene scene = new Scene(root, 400, 300);
        
        // Load external CSS stylesheet
        scene.getStylesheets().add(
            HelloWorldCssApp.class.getResource("/com/example/javafx/styles.css").toExternalForm()
        );
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
