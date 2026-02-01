package com.example.javafx;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Controller for TodoList.fxml
 * 
 * Demonstrates:
 * - Implementing Initializable interface
 * - @FXML field injection
 * - @FXML method handlers
 * - Controller initialization
 */
public class TodoListController implements Initializable {
    
    @FXML
    private TextField inputField;
    
    @FXML
    private Button addButton;
    
    @FXML
    private Button deleteButton;
    
    @FXML
    private Button clearButton;
    
    @FXML
    private ListView<String> todoListView;
    
    private List<String> todos;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize data model
        todos = new ArrayList<>();
        
        // Set up initial state
        inputField.setPromptText("Enter a new task...");
    }
    
    @FXML
    private void handleAddTodo() {
        String task = inputField.getText().trim();
        if (!task.isEmpty()) {
            todos.add(task);
            updateListView();
            inputField.clear();
            inputField.requestFocus();
        }
    }
    
    @FXML
    private void handleDeleteSelected() {
        int selectedIndex = todoListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            todos.remove(selectedIndex);
            updateListView();
        }
    }
    
    @FXML
    private void handleClearAll() {
        todos.clear();
        updateListView();
    }
    
    private void updateListView() {
        todoListView.getItems().clear();
        todoListView.getItems().addAll(todos);
    }
}
