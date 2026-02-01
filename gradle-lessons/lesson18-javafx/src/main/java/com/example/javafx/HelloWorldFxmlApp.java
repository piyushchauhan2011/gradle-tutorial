package com.example.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * HelloWorld Application using FXML
 * 
 * Demonstrates:
 * - Loading FXML files
 * - FXML-based UI definition
 * - Controller pattern
 * - CSS styling from external file
 */
public class HelloWorldFxmlApp extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        // Load FXML file
        FXMLLoader fxmlLoader = new FXMLLoader(
            HelloWorldFxmlApp.class.getResource("/com/example/javafx/HelloWorld.fxml")
        );
        
        // Create scene from FXML
        Scene scene = new Scene(fxmlLoader.load(), 400, 300);
        
        // Load CSS stylesheet
        scene.getStylesheets().add(
            HelloWorldFxmlApp.class.getResource("/com/example/javafx/styles.css").toExternalForm()
        );
        
        // Configure stage
        primaryStage.setTitle("JavaFX Hello World (FXML) - Lesson 18");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
