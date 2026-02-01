package com.example.javafx;

/**
 * Calculator logic separated from UI
 * This class can be tested independently
 */
public class CalculatorLogic {
    
    private double currentValue = 0;
    private String currentOperation = "";
    private boolean startNewNumber = true;
    
    public String processNumber(String displayText, String number) {
        if (startNewNumber) {
            startNewNumber = false;
            return number;
        } else {
            if (number.equals(".") && displayText.contains(".")) {
                return displayText; // Don't allow multiple decimal points
            }
            return displayText + number;
        }
    }
    
    public void setOperation(String operation, String displayText) {
        if (!startNewNumber) {
            calculate(displayText);
        }
        currentOperation = operation;
        currentValue = Double.parseDouble(displayText);
        startNewNumber = true;
    }
    
    public String calculate(String displayText) {
        if (currentOperation.isEmpty()) {
            return displayText;
        }
        
        double secondValue = Double.parseDouble(displayText);
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
                if (secondValue == 0) {
                    currentOperation = "";
                    startNewNumber = true;
                    throw new ArithmeticException("Division by zero");
                }
                result = currentValue / secondValue;
                break;
        }
        
        currentOperation = "";
        startNewNumber = true;
        
        // Format result
        if (result == (long) result) {
            return String.valueOf((long) result);
        } else {
            return String.valueOf(result);
        }
    }
    
    public void clear() {
        currentValue = 0;
        currentOperation = "";
        startNewNumber = true;
    }
    
    public boolean isStartNewNumber() {
        return startNewNumber;
    }
    
    public String getCurrentOperation() {
        return currentOperation;
    }
}
