package com.example.javafx;

import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.TextInputControlMatchers.hasText;

/**
 * UI Tests for CalculatorApp using TestFX
 */
public class CalculatorAppUiTest extends ApplicationTest {
    
    @Override
    public void start(Stage stage) {
        new CalculatorApp().start(stage);
    }
    
    @Test
    public void testInitialDisplay() {
        WaitForAsyncUtils.waitForFxEvents();
        verifyThat("#display", hasText("0"));
    }
    
    @Test
    public void testNumberInput() {
        WaitForAsyncUtils.waitForFxEvents();
        // Click number buttons
        clickOn("#button1");
        WaitForAsyncUtils.waitForFxEvents();
        clickOn("#button2");
        WaitForAsyncUtils.waitForFxEvents();
        clickOn("#button3");
        WaitForAsyncUtils.waitForFxEvents();
        
        verifyThat("#display", hasText("123"));
    }
    
    @Test
    public void testAddition() {
        WaitForAsyncUtils.waitForFxEvents();
        // 5 + 3 = 8
        clickOn("#button5");
        WaitForAsyncUtils.waitForFxEvents();
        clickOn("#buttonPlus");
        WaitForAsyncUtils.waitForFxEvents();
        clickOn("#button3");
        WaitForAsyncUtils.waitForFxEvents();
        clickOn("#equalsButton");
        WaitForAsyncUtils.waitForFxEvents();
        
        verifyThat("#display", hasText("8"));
    }
    
    @Test
    public void testSubtraction() {
        WaitForAsyncUtils.waitForFxEvents();
        // 10 - 4 = 6
        clickOn("#button1");
        WaitForAsyncUtils.waitForFxEvents();
        clickOn("#button0");
        WaitForAsyncUtils.waitForFxEvents();
        clickOn("#buttonMinus");
        WaitForAsyncUtils.waitForFxEvents();
        clickOn("#button4");
        WaitForAsyncUtils.waitForFxEvents();
        clickOn("#equalsButton");
        WaitForAsyncUtils.waitForFxEvents();
        
        verifyThat("#display", hasText("6"));
    }
    
    @Test
    public void testMultiplication() {
        WaitForAsyncUtils.waitForFxEvents();
        // 6 * 7 = 42
        clickOn("#button6");
        WaitForAsyncUtils.waitForFxEvents();
        clickOn("#buttonMultiply");
        WaitForAsyncUtils.waitForFxEvents();
        clickOn("#button7");
        WaitForAsyncUtils.waitForFxEvents();
        clickOn("#equalsButton");
        WaitForAsyncUtils.waitForFxEvents();
        
        verifyThat("#display", hasText("42"));
    }
    
    @Test
    public void testDivision() {
        WaitForAsyncUtils.waitForFxEvents();
        // 20 / 4 = 5
        clickOn("#button2");
        WaitForAsyncUtils.waitForFxEvents();
        clickOn("#button0");
        WaitForAsyncUtils.waitForFxEvents();
        clickOn("#buttonDivide");
        WaitForAsyncUtils.waitForFxEvents();
        clickOn("#button4");
        WaitForAsyncUtils.waitForFxEvents();
        clickOn("#equalsButton");
        WaitForAsyncUtils.waitForFxEvents();
        
        verifyThat("#display", hasText("5"));
    }
    
    @Test
    public void testClear() {
        WaitForAsyncUtils.waitForFxEvents();
        // Enter some numbers
        clickOn("#button5");
        WaitForAsyncUtils.waitForFxEvents();
        clickOn("#button6");
        WaitForAsyncUtils.waitForFxEvents();
        clickOn("#button7");
        WaitForAsyncUtils.waitForFxEvents();
        
        // Clear
        clickOn("#clearButton");
        WaitForAsyncUtils.waitForFxEvents();
        
        verifyThat("#display", hasText("0"));
    }
    
    @Test
    public void testDecimalInput() {
        WaitForAsyncUtils.waitForFxEvents();
        // Enter 3.14
        clickOn("#button3");
        WaitForAsyncUtils.waitForFxEvents();
        clickOn("#buttonDecimal");
        WaitForAsyncUtils.waitForFxEvents();
        clickOn("#button1");
        WaitForAsyncUtils.waitForFxEvents();
        clickOn("#button4");
        WaitForAsyncUtils.waitForFxEvents();
        
        verifyThat("#display", hasText("3.14"));
    }
    
    @Test
    public void testMultipleOperations() {
        WaitForAsyncUtils.waitForFxEvents();
        // 2 + 3 = 5, then * 4 = 20
        clickOn("#button2");
        WaitForAsyncUtils.waitForFxEvents();
        clickOn("#buttonPlus");
        WaitForAsyncUtils.waitForFxEvents();
        clickOn("#button3");
        WaitForAsyncUtils.waitForFxEvents();
        clickOn("#equalsButton");
        WaitForAsyncUtils.waitForFxEvents();
        
        verifyThat("#display", hasText("5"));
        
        clickOn("#buttonMultiply");
        WaitForAsyncUtils.waitForFxEvents();
        clickOn("#button4");
        WaitForAsyncUtils.waitForFxEvents();
        clickOn("#equalsButton");
        WaitForAsyncUtils.waitForFxEvents();
        
        verifyThat("#display", hasText("20"));
    }
}
