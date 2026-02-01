package com.example.javafx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * Todo List Application
 * 
 * Demonstrates:
 * - ListView for displaying items
 * - TextField and Button for input
 * - Dynamic list management
 * - Event handling for multiple actions
 * - HBox and VBox layouts
 */
public class TodoListApp extends Application {
    
    private ListView<String> todoListView;
    private TextField inputField;
    private List<String> todos;
    
    @Override
    public void start(Stage primaryStage) {
        System.out.println("TodoListApp.start() called");
        try {
            primaryStage.setTitle("JavaFX Todo List - Lesson 18");
            
            todos = new ArrayList<>();
        
        // Create input area
        inputField = new TextField();
        inputField.setPromptText("Enter a new task...");
        inputField.setPrefWidth(300);
        inputField.setOnAction(e -> addTodo());
        
        Button addButton = new Button("Add");
        addButton.setStyle("-fx-background-color: #4ecdc4; -fx-text-fill: white; -fx-font-size: 14px;");
        addButton.setOnAction(e -> addTodo());
        
        Button deleteButton = new Button("Delete Selected");
        deleteButton.setStyle("-fx-background-color: #ff6b6b; -fx-text-fill: white; -fx-font-size: 14px;");
        deleteButton.setOnAction(e -> deleteSelected());
        
        Button clearButton = new Button("Clear All");
        clearButton.setStyle("-fx-background-color: #95a5a6; -fx-text-fill: white; -fx-font-size: 14px;");
        clearButton.setOnAction(e -> clearAll());
        
        HBox inputBox = new HBox(10);
        inputBox.setAlignment(Pos.CENTER);
        inputBox.getChildren().addAll(inputField, addButton);
        
        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(deleteButton, clearButton);
        
        // Create ListView
        todoListView = new ListView<>();
        todoListView.setPrefHeight(300);
        todoListView.setStyle("-fx-font-size: 14px;");
        
        // Create label
        Label titleLabel = new Label("My Todo List");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-padding: 10px;");
        
        // Layout
        VBox root = new VBox(15);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(titleLabel, inputBox, todoListView, buttonBox);
        
        Scene scene = new Scene(root, 500, 500);
        primaryStage.setScene(scene);
        
        System.out.println("About to show stage...");
        // Ensure window is visible
        primaryStage.setX(100);
        primaryStage.setY(100);
        primaryStage.show();
        System.out.println("Stage shown");
        
        // Force window to front
        primaryStage.toFront();
        primaryStage.requestFocus();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error starting application: " + e.getMessage());
        }
    }
    
    @Override
    public void init() {
        System.out.println("TodoListApp.init() called");
    }
    
    private void addTodo() {
        String task = inputField.getText().trim();
        if (!task.isEmpty()) {
            todos.add(task);
            updateListView();
            inputField.clear();
            inputField.requestFocus();
        }
    }
    
    private void deleteSelected() {
        int selectedIndex = todoListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            todos.remove(selectedIndex);
            updateListView();
        }
    }
    
    private void clearAll() {
        todos.clear();
        updateListView();
    }
    
    private void updateListView() {
        todoListView.getItems().clear();
        todoListView.getItems().addAll(todos);
    }
    
    public static void main(String[] args) {
        System.out.println("TodoListApp.main() called");
        System.out.println("OS: " + System.getProperty("os.name"));
        System.out.println("Java version: " + System.getProperty("java.version"));
        try {
            launch(args);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error launching application: " + e.getMessage());
        }
    }
}
