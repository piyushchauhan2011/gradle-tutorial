package com.example.javafx;

import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.ListViewMatchers.hasItems;
import static org.testfx.matcher.control.ListViewMatchers.isEmpty;

/**
 * UI Tests for TodoListApp using TestFX
 */
public class TodoListAppUiTest extends ApplicationTest {
    
    @Override
    public void start(Stage stage) {
        new TodoListApp().start(stage);
    }
    
    @Test
    public void testInitialState() {
        WaitForAsyncUtils.waitForFxEvents();
        // Verify list is empty initially
        verifyThat("#todoListView", isEmpty());
    }
    
    @Test
    public void testAddTodo() {
        WaitForAsyncUtils.waitForFxEvents();
        // Add a todo item
        clickOn("#inputField").write("Buy groceries");
        WaitForAsyncUtils.waitForFxEvents();
        clickOn("#addButton");
        WaitForAsyncUtils.waitForFxEvents();
        
        // Verify item was added
        verifyThat("#todoListView", hasItems(1));
    }
    
    @Test
    public void testAddMultipleTodos() {
        WaitForAsyncUtils.waitForFxEvents();
        // Add multiple todos
        clickOn("#inputField").write("Task 1");
        WaitForAsyncUtils.waitForFxEvents();
        clickOn("#addButton");
        WaitForAsyncUtils.waitForFxEvents();
        
        clickOn("#inputField").write("Task 2");
        WaitForAsyncUtils.waitForFxEvents();
        clickOn("#addButton");
        WaitForAsyncUtils.waitForFxEvents();
        
        clickOn("#inputField").write("Task 3");
        WaitForAsyncUtils.waitForFxEvents();
        clickOn("#addButton");
        WaitForAsyncUtils.waitForFxEvents();
        
        // Verify all items were added
        verifyThat("#todoListView", hasItems(3));
    }
    
    @Test
    public void testAddTodoViaEnterKey() {
        WaitForAsyncUtils.waitForFxEvents();
        // Add todo by pressing Enter in text field
        clickOn("#inputField").write("Press Enter task");
        WaitForAsyncUtils.waitForFxEvents();
        press(javafx.scene.input.KeyCode.ENTER);
        WaitForAsyncUtils.waitForFxEvents();
        
        verifyThat("#todoListView", hasItems(1));
    }
    
    @Test
    public void testDeleteSelectedTodo() {
        WaitForAsyncUtils.waitForFxEvents();
        // Add some todos
        clickOn("#inputField").write("Task to delete");
        WaitForAsyncUtils.waitForFxEvents();
        clickOn("#addButton");
        WaitForAsyncUtils.waitForFxEvents();
        
        clickOn("#inputField").write("Task to keep");
        WaitForAsyncUtils.waitForFxEvents();
        clickOn("#addButton");
        WaitForAsyncUtils.waitForFxEvents();
        
        // Select first item and delete
        clickOn("#todoListView");
        WaitForAsyncUtils.waitForFxEvents();
        push(javafx.scene.input.KeyCode.DOWN); // Select first item
        WaitForAsyncUtils.waitForFxEvents();
        clickOn("#deleteButton");
        WaitForAsyncUtils.waitForFxEvents();
        
        // Verify one item remains
        verifyThat("#todoListView", hasItems(1));
    }
    
    @Test
    public void testClearAllTodos() {
        WaitForAsyncUtils.waitForFxEvents();
        // Add multiple todos
        clickOn("#inputField").write("Task 1");
        WaitForAsyncUtils.waitForFxEvents();
        clickOn("#addButton");
        WaitForAsyncUtils.waitForFxEvents();
        
        clickOn("#inputField").write("Task 2");
        WaitForAsyncUtils.waitForFxEvents();
        clickOn("#addButton");
        WaitForAsyncUtils.waitForFxEvents();
        
        // Clear all
        clickOn("#clearButton");
        WaitForAsyncUtils.waitForFxEvents();
        
        // Verify list is empty
        verifyThat("#todoListView", isEmpty());
    }
    
    @Test
    public void testEmptyInputNotAdded() {
        WaitForAsyncUtils.waitForFxEvents();
        // Try to add empty string
        clickOn("#inputField").write("   "); // Only spaces
        WaitForAsyncUtils.waitForFxEvents();
        clickOn("#addButton");
        WaitForAsyncUtils.waitForFxEvents();
        
        // Verify nothing was added
        verifyThat("#todoListView", isEmpty());
    }
    
    @Test
    public void testInputFieldClearedAfterAdd() {
        WaitForAsyncUtils.waitForFxEvents();
        // Add a todo
        clickOn("#inputField").write("Test task");
        WaitForAsyncUtils.waitForFxEvents();
        clickOn("#addButton");
        WaitForAsyncUtils.waitForFxEvents();
        
        // Verify input field is cleared
        verifyThat("#inputField", org.testfx.matcher.control.TextInputControlMatchers.hasText(""));
    }
}
