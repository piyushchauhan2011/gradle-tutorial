import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

/**
 * Tests for TodoList logic
 */
public class TodoListLogicTest {
    
    private List<String> todos;
    
    @Before
    public void setUp() {
        todos = new ArrayList<>();
    }
    
    @Test
    public void testAddTodo() {
        todos.add("Buy groceries");
        assertEquals("Should have one todo", 1, todos.size());
        assertEquals("Todo should match", "Buy groceries", todos.get(0));
    }
    
    @Test
    public void testAddMultipleTodos() {
        todos.add("Task 1");
        todos.add("Task 2");
        todos.add("Task 3");
        assertEquals("Should have three todos", 3, todos.size());
    }
    
    @Test
    public void testDeleteTodo() {
        todos.add("Task 1");
        todos.add("Task 2");
        todos.add("Task 3");
        
        todos.remove(1);
        
        assertEquals("Should have two todos", 2, todos.size());
        assertEquals("First todo should remain", "Task 1", todos.get(0));
        assertEquals("Third todo should be second now", "Task 3", todos.get(1));
    }
    
    @Test
    public void testClearAll() {
        todos.add("Task 1");
        todos.add("Task 2");
        todos.add("Task 3");
        
        todos.clear();
        
        assertEquals("Should have no todos", 0, todos.size());
        assertTrue("List should be empty", todos.isEmpty());
    }
    
    @Test
    public void testEmptyTodoList() {
        assertTrue("New list should be empty", todos.isEmpty());
        assertEquals("New list should have size 0", 0, todos.size());
    }
    
    @Test
    public void testTodoOrder() {
        todos.add("First");
        todos.add("Second");
        todos.add("Third");
        
        assertEquals("First item", "First", todos.get(0));
        assertEquals("Second item", "Second", todos.get(1));
        assertEquals("Third item", "Third", todos.get(2));
    }
    
    @Test
    public void testDeleteFirstTodo() {
        todos.add("First");
        todos.add("Second");
        todos.add("Third");
        
        todos.remove(0);
        
        assertEquals("Should have two todos", 2, todos.size());
        assertEquals("Second should be first now", "Second", todos.get(0));
    }
    
    @Test
    public void testDeleteLastTodo() {
        todos.add("First");
        todos.add("Second");
        todos.add("Third");
        
        todos.remove(2);
        
        assertEquals("Should have two todos", 2, todos.size());
        assertEquals("Last should be Second now", "Second", todos.get(1));
    }
}
