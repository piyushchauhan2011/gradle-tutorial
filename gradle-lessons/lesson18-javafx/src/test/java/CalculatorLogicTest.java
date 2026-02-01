import com.example.javafx.CalculatorLogic;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for CalculatorLogic class
 */
public class CalculatorLogicTest {
    
    private CalculatorLogic calculator;
    
    @Before
    public void setUp() {
        calculator = new CalculatorLogic();
    }
    
    @Test
    public void testProcessNumber_FirstNumber() {
        String result = calculator.processNumber("0", "5");
        assertEquals("Should set first number", "5", result);
        assertFalse("Should not start new number after input", calculator.isStartNewNumber());
    }
    
    @Test
    public void testProcessNumber_AppendNumber() {
        calculator.processNumber("0", "5");
        String result = calculator.processNumber("5", "3");
        assertEquals("Should append number", "53", result);
    }
    
    @Test
    public void testProcessNumber_DecimalPoint() {
        calculator.processNumber("0", "5");
        String result = calculator.processNumber("5", ".");
        assertEquals("Should add decimal point", "5.", result);
    }
    
    @Test
    public void testProcessNumber_MultipleDecimalPoints() {
        calculator.processNumber("0", "5");
        calculator.processNumber("5", ".");
        String result = calculator.processNumber("5.", ".");
        assertEquals("Should not add second decimal point", "5.", result);
    }
    
    @Test
    public void testAddition() {
        calculator.processNumber("0", "10");
        calculator.setOperation("+", "10");
        String result = calculator.calculate("5");
        assertEquals("10 + 5 should equal 15", "15", result);
    }
    
    @Test
    public void testSubtraction() {
        calculator.processNumber("0", "10");
        calculator.setOperation("-", "10");
        String result = calculator.calculate("3");
        assertEquals("10 - 3 should equal 7", "7", result);
    }
    
    @Test
    public void testMultiplication() {
        calculator.processNumber("0", "5");
        calculator.setOperation("*", "5");
        String result = calculator.calculate("4");
        assertEquals("5 * 4 should equal 20", "20", result);
    }
    
    @Test
    public void testDivision() {
        calculator.processNumber("0", "20");
        calculator.setOperation("/", "20");
        String result = calculator.calculate("4");
        assertEquals("20 / 4 should equal 5", "5", result);
    }
    
    @Test(expected = ArithmeticException.class)
    public void testDivisionByZero() {
        calculator.processNumber("0", "10");
        calculator.setOperation("/", "10");
        calculator.calculate("0");
    }
    
    @Test
    public void testDecimalResult() {
        calculator.processNumber("0", "10");
        calculator.setOperation("/", "10");
        String result = calculator.calculate("3");
        assertEquals("10 / 3 should show decimal", "3.3333333333333335", result);
    }
    
    @Test
    public void testIntegerResult() {
        calculator.processNumber("0", "10");
        calculator.setOperation("/", "10");
        String result = calculator.calculate("2");
        assertEquals("10 / 2 should show integer", "5", result);
    }
    
    @Test
    public void testClear() {
        calculator.processNumber("0", "5");
        calculator.setOperation("+", "5");
        calculator.clear();
        assertTrue("Should start new number after clear", calculator.isStartNewNumber());
        assertEquals("Operation should be cleared", "", calculator.getCurrentOperation());
    }
    
    @Test
    public void testMultipleOperations() {
        // 10 + 5 = 15, then * 2 = 30
        calculator.processNumber("0", "10");
        calculator.setOperation("+", "10");
        String result1 = calculator.calculate("5");
        assertEquals("First calculation", "15", result1);
        
        calculator.setOperation("*", result1);
        String result2 = calculator.calculate("2");
        assertEquals("Second calculation", "30", result2);
    }
}
