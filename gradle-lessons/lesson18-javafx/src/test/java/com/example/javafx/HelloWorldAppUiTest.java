package com.example.javafx;

import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.control.LabeledMatchers;
import org.testfx.util.WaitForAsyncUtils;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

/**
 * UI Tests for HelloWorldApp using TestFX
 */
public class HelloWorldAppUiTest extends ApplicationTest {
    
    @Override
    public void start(Stage stage) {
        new HelloWorldApp().start(stage);
    }
    
    @Test
    public void testInitialMessage() {
        WaitForAsyncUtils.waitForFxEvents();
        verifyThat("#messageLabel", hasText("Hello, JavaFX!"));
    }
    
    @Test
    public void testButtonClick() {
        WaitForAsyncUtils.waitForFxEvents();
        // Click the button
        clickOn("#clickButton");
        WaitForAsyncUtils.waitForFxEvents();
        
        // Verify message changed
        verifyThat("#messageLabel", hasText("Button clicked 1 time(s)!"));
    }
    
    @Test
    public void testMultipleClicks() {
        WaitForAsyncUtils.waitForFxEvents();
        // Click multiple times
        clickOn("#clickButton");
        WaitForAsyncUtils.waitForFxEvents();
        clickOn("#clickButton");
        WaitForAsyncUtils.waitForFxEvents();
        clickOn("#clickButton");
        WaitForAsyncUtils.waitForFxEvents();
        
        // Verify count is correct
        verifyThat("#messageLabel", hasText("Button clicked 3 time(s)!"));
    }
    
    @Test
    public void testButtonExists() {
        WaitForAsyncUtils.waitForFxEvents();
        // Verify button is visible and clickable
        verifyThat("#clickButton", LabeledMatchers.hasText("Click Me!"));
    }
}
