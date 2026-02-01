package com.example.javafx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Simple Calculator Application
 * 
 * Demonstrates:
 * - GridPane layout
 * - TextField for input/output
 * - Multiple buttons with event handlers
 * - Calculator logic
 * - CSS styling
 */
public class CalculatorApp extends Application {
    
    private TextField display;
    private double currentValue = 0;
    private String currentOperation = "";
    private boolean startNewNumber = true;
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("JavaFX Calculator - Lesson 18");
        
        // Create display field
        display = new TextField("0");
        display.setId("display");
        display.setEditable(false);
        display.getStyleClass().add("calculator-display");
        display.setPrefHeight(60);
        
        // Create button grid
        GridPane buttonGrid = new GridPane();
        buttonGrid.setHgap(5);
        buttonGrid.setVgap(5);
        buttonGrid.setAlignment(Pos.CENTER);
        
        // Number buttons
        String[] buttonLabels = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+"
        };
        
        int row = 0;
        int col = 0;
        for (String label : buttonLabels) {
            Button btn = createButton(label);
            buttonGrid.add(btn, col, row);
            col++;
            if (col == 4) {
                col = 0;
                row++;
            }
        }
        
        // Clear button
        Button clearBtn = createButton("C");
        clearBtn.setId("clearButton");
        clearBtn.getStyleClass().clear(); // Remove default styles
        clearBtn.getStyleClass().add("calculator-button");
        clearBtn.getStyleClass().add("clear-button");
        clearBtn.setOnAction(e -> clear());
        buttonGrid.add(clearBtn, 0, 4, 4, 1);
        
        // Layout
        VBox root = new VBox(10);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(display, buttonGrid);
        
        Scene scene = new Scene(root, 300, 400);
        
        // Load external CSS stylesheet
        scene.getStylesheets().add(
            CalculatorApp.class.getResource("/com/example/javafx/calculator.css").toExternalForm()
        );
        
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    
    private Button createButton(String text) {
        Button btn = new Button(text);
        // Set ID for testing - use safe IDs for operators and special characters
        String buttonId = text.equals("+") ? "buttonPlus" :
                         text.equals("-") ? "buttonMinus" :
                         text.equals("*") ? "buttonMultiply" :
                         text.equals("/") ? "buttonDivide" :
                         text.equals("=") ? "equalsButton" :
                         text.equals(".") ? "buttonDecimal" :
                         "button" + text;
        btn.setId(buttonId);
        btn.setPrefSize(60, 60);
        
        // Apply CSS classes based on button type
        btn.getStyleClass().add("calculator-button");
        
        if (text.matches("[0-9.]")) {
            btn.getStyleClass().add("number-button");
            btn.setOnAction(e -> numberPressed(text));
        } else if (text.equals("=")) {
            btn.getStyleClass().add("equals-button");
            btn.setOnAction(e -> calculate());
        } else {
            btn.getStyleClass().add("operator-button");
            btn.setOnAction(e -> operationPressed(text));
        }
        
        return btn;
    }
    
    private void numberPressed(String number) {
        if (startNewNumber) {
            display.setText(number);
            startNewNumber = false;
        } else {
            if (number.equals(".") && display.getText().contains(".")) {
                return; // Don't allow multiple decimal points
            }
            display.setText(display.getText() + number);
        }
    }
    
    private void operationPressed(String operation) {
        if (!startNewNumber) {
            calculate();
        }
        currentOperation = operation;
        currentValue = Double.parseDouble(display.getText());
        startNewNumber = true;
    }
    
    private void calculate() {
        if (currentOperation.isEmpty()) {
            return;
        }
        
        double secondValue = Double.parseDouble(display.getText());
        double result = 0;
        
        switch (currentOperation) {
            case "+":
                result = currentValue + secondValue;
                break;
            case "-":
                result = currentValue - secondValue;
                break;
            case "*":
                result = currentValue * secondValue;
                break;
            case "/":
                if (secondValue != 0) {
                    result = currentValue / secondValue;
                } else {
                    display.setText("Error");
                    clear();
                    return;
                }
                break;
        }
        
        // Format result
        if (result == (long) result) {
            display.setText(String.valueOf((long) result));
        } else {
            display.setText(String.valueOf(result));
        }
        
        currentOperation = "";
        startNewNumber = true;
    }
    
    private void clear() {
        display.setText("0");
        currentValue = 0;
        currentOperation = "";
        startNewNumber = true;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
