package com.example.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * TodoList Application using FXML
 * 
 * Demonstrates:
 * - Complex FXML layout with multiple containers
 * - Controller with multiple event handlers
 * - CSS styling applied to FXML elements
 */
public class TodoListFxmlApp extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        // Load FXML file
        FXMLLoader fxmlLoader = new FXMLLoader(
            TodoListFxmlApp.class.getResource("/com/example/javafx/TodoList.fxml")
        );
        
        // Create scene from FXML
        Scene scene = new Scene(fxmlLoader.load(), 500, 500);
        
        // Load CSS stylesheet
        scene.getStylesheets().add(
            TodoListFxmlApp.class.getResource("/com/example/javafx/styles.css").toExternalForm()
        );
        
        // Configure stage
        primaryStage.setTitle("JavaFX Todo List (FXML) - Lesson 18");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
