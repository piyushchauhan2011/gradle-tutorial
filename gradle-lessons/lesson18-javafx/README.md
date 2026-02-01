# Lesson 18: JavaFX Desktop Applications

> **⚠️ macOS Issue**: There's currently a known issue with JavaFX windows not appearing on macOS when run via Gradle. The code compiles and tests pass, but the window may not display. See [QUICKSTART.md](QUICKSTART.md) for details and workarounds. For reliable GUI development on macOS, use Lesson 14 (Swing) or Lesson 17 (libGDX) instead.

## Description
This lesson introduces you to JavaFX, the modern successor to Swing for building rich desktop applications in Java. Learn how to create beautiful, responsive user interfaces with layouts, controls, styling, and event handling. JavaFX provides a powerful API for creating desktop applications with modern UI/UX principles.

## Key Concepts

### 1. JavaFX Overview
- **What is JavaFX?**: Modern GUI framework for Java desktop applications
- **Architecture**: Scene Graph-based rendering, CSS styling, FXML for UI design
- **Advantages**: Modern look, CSS styling, animations, charts, 3D support
- **Platform Support**: Windows, macOS, Linux

### 2. Application Lifecycle
- **Application Class**: Extend `Application` and override `start()` method
- **Stage**: Top-level container (window)
- **Scene**: Container for scene graph (content)
- **Scene Graph**: Tree of nodes representing UI elements
- **launch()**: Static method to start JavaFX application

### 3. Layout Containers
- **VBox**: Vertical layout - stacks children vertically
- **HBox**: Horizontal layout - stacks children horizontally
- **GridPane**: Grid layout - arranges children in rows and columns
- **BorderPane**: Divides into 5 regions (top, bottom, left, right, center)
- **FlowPane**: Wraps children to next line when needed
- **StackPane**: Stacks children on top of each other

### 4. Controls
- **Label**: Display text
- **Button**: Clickable button
- **TextField**: Single-line text input
- **TextArea**: Multi-line text input
- **ListView**: Display list of items
- **ComboBox**: Dropdown selection
- **CheckBox**: Boolean selection
- **RadioButton**: Single selection from group
- **Slider**: Numeric input with slider
- **ProgressBar**: Show progress

### 5. Event Handling
- **setOnAction()**: Handle button clicks and actions
- **setOnMouseClicked()**: Handle mouse clicks
- **setOnKeyPressed()**: Handle keyboard input
- **Event Objects**: Access event information
- **Lambda Expressions**: Modern way to handle events

### 6. Styling
- **Inline Styles**: `setStyle("-fx-property: value;")`
- **CSS Files**: External stylesheets
- **CSS Selectors**: Style by class, ID, or type
- **JavaFX CSS**: Special properties prefixed with `-fx-`

## Runnable Examples

### Example 1: Hello World (`HelloWorldApp`)
Basic JavaFX application demonstrating fundamentals:

```bash
./gradlew -p lesson18-javafx run
```

**Features:**
- Window creation
- Label and Button controls
- VBox layout
- Event handling
- Inline CSS styling

**Key Learning Points:**
- Application structure
- Stage and Scene setup
- Basic controls
- Event handling with lambda expressions

### Example 2: Calculator (`CalculatorApp`)
Full-featured calculator application:

Modify `build.gradle` to change main class:
```groovy
application {
    mainClass = 'com.example.javafx.CalculatorApp'
}
```

**Features:**
- GridPane layout for button grid
- TextField for display
- Calculator logic (addition, subtraction, multiplication, division)
- Error handling (division by zero)
- CSS styling for different button types

**Key Learning Points:**
- Complex layouts (GridPane)
- Multiple controls working together
- Separating UI from logic
- Input validation

### Example 3: Todo List (`TodoListApp`)
Interactive todo list application:

Modify `build.gradle` to change main class:
```groovy
application {
    mainClass = 'com.example.javafx.TodoListApp'
}
```

**Features:**
- ListView for displaying todos
- TextField for input
- Add, delete, and clear functionality
- Dynamic list management

**Key Learning Points:**
- ListView usage
- Dynamic data management
- Multiple event handlers
- HBox and VBox combination

## Code Structure

### `HelloWorldApp.java` - Basic Example

#### Application Structure
```java
public class HelloWorldApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        // 1. Create controls
        // 2. Create layout
        // 3. Create scene
        // 4. Set scene on stage
        // 5. Show stage
    }
    
    public static void main(String[] args) {
        launch(args); // Start JavaFX application
    }
}
```

#### Key Components
- **Stage**: The window (`primaryStage`)
- **Scene**: Container for UI (`new Scene(root, width, height)`)
- **VBox**: Vertical layout container
- **Controls**: Label, Button
- **Event Handling**: `setOnAction(e -> { ... })`

### `CalculatorApp.java` - Advanced Example

#### Layout Structure
- **VBox**: Main container (vertical)
- **TextField**: Display area
- **GridPane**: Button grid (4 columns)
- **Buttons**: Number buttons, operation buttons, equals button

#### Logic Separation
- Calculator logic separated into `CalculatorLogic` class
- UI handles display and user interaction
- Logic handles calculations
- Makes code testable

### `TodoListApp.java` - List Management

#### Components
- **ListView**: Display todos
- **TextField**: Input new todos
- **Buttons**: Add, Delete, Clear
- **List<String>**: Backend data storage

#### Features
- Add todos with Enter key or button
- Delete selected todo
- Clear all todos
- Dynamic list updates

## Exercises

### Beginner

1. **Modify Hello World**
   - Change button text and colors
   - Add more buttons
   - Change window size
   - Add more labels

2. **Create a Simple Form**
   - Name input field
   - Email input field
   - Submit button
   - Display entered information

3. **Color Picker**
   - Create buttons for different colors
   - Change background color when clicked
   - Use CSS for styling

4. **Counter Application**
   - Display a number
   - Increment button (+)
   - Decrement button (-)
   - Reset button

### Intermediate

5. **Enhanced Calculator**
   - Add more operations (%, √, x²)
   - Add keyboard support
   - Add history display
   - Improve error handling

6. **Temperature Converter**
   - Input field for temperature
   - Radio buttons for unit (Celsius/Fahrenheit)
   - Convert button
   - Display result

7. **Password Generator**
   - Slider for password length
   - Checkboxes for options (uppercase, numbers, symbols)
   - Generate button
   - Display generated password
   - Copy to clipboard button

8. **Expense Tracker**
   - ListView for expenses
   - Input fields for amount and description
   - Add expense button
   - Calculate total
   - Delete expense functionality

### Advanced

9. **Text Editor**
   - TextArea for editing
   - MenuBar with File menu
   - Open/Save file dialogs
   - Find/Replace functionality
   - Word count display

10. **Data Visualization**
    - Use JavaFX Charts (LineChart, BarChart)
    - Display data from arrays or files
    - Interactive chart controls
    - Multiple chart types

11. **FXML Application**
    - Create FXML file for UI design
    - Use Scene Builder (visual tool)
    - Separate UI design from logic
    - Controller class for event handling

12. **Animation Application**
    - Animate shapes moving
    - Fade in/out effects
    - Rotate and scale animations
    - Timeline for complex animations

## Testing

Run the test suite:

```bash
./gradlew -p lesson18-javafx test
```

The tests cover:
- **CalculatorLogic**: All calculator operations, edge cases
- **TodoListLogic**: List management, add/delete/clear operations

**Note**: UI testing with TestFX is included in dependencies but requires JavaFX application to be running. The logic classes are fully testable independently.

## Key Takeaways

1. **Application Structure**: Extend `Application`, override `start()`, call `launch()`
2. **Scene Graph**: Tree structure of nodes (controls, layouts, shapes)
3. **Layouts**: Choose appropriate layout for your UI structure
4. **Event Handling**: Use lambda expressions for clean event handlers
5. **Separation of Concerns**: Separate UI from business logic
6. **CSS Styling**: Use CSS for consistent, maintainable styling
7. **Modern UI**: JavaFX provides modern, attractive default styling
8. **Responsive Design**: Layouts adapt to window resizing

## JavaFX vs Swing

| Feature | JavaFX | Swing |
|---------|--------|-------|
| **Look & Feel** | Modern, CSS-stylable | Platform-specific or custom |
| **Rendering** | Hardware-accelerated | Software rendering |
| **CSS Support** | Full CSS support | Limited |
| **FXML** | Declarative UI design | Code-only |
| **Animations** | Built-in animation API | Manual implementation |
| **3D Support** | Built-in 3D | Not available |
| **Charts** | Built-in chart library | Third-party libraries |
| **Learning Curve** | Moderate | Steeper |
| **Maturity** | Modern, actively developed | Legacy, stable |

## Building and Running

### Prerequisites
- Java Development Kit (JDK) 11 or later
- Gradle (included as wrapper `./gradlew`)

### Build
```bash
./gradlew -p lesson18-javafx build
```

### Run Hello World Example
```bash
./gradlew -p lesson18-javafx run
```

### Run Calculator Example
Modify `build.gradle`:
```groovy
application {
    mainClass = 'com.example.javafx.CalculatorApp'
}
```

### Run Todo List Example
Modify `build.gradle`:
```groovy
application {
    mainClass = 'com.example.javafx.TodoListApp'
}
```

### Run Tests
```bash
./gradlew -p lesson18-javafx test
```

## Known Issues

### macOS: Window Not Appearing

**Current Issue**: On macOS (especially Apple Silicon), the JavaFX window may not appear even though the app runs. This is a known issue with JavaFX + Gradle + macOS combination.

**Workaround**: Until this is resolved, consider:
1. Using Swing (Lesson 14) for GUI applications on macOS
2. Waiting for JavaFX plugin updates
3. Running JavaFX apps directly with `java` command outside of Gradle

We're investigating this issue and will update the lesson when a solution is found.

## Common Issues and Solutions

### Issue: "JavaFX runtime components are missing"
- **Solution**: Ensure JavaFX dependencies are in `build.gradle`
- Check that `--add-modules` is specified in run configuration
- Verify JavaFX version matches your Java version

### Issue: "Application doesn't start"
- **Solution**: Check that `main()` method calls `launch(args)`
- Verify main class is set correctly in `build.gradle`
- Check console for error messages

### Issue: "Controls not visible"
- **Solution**: Ensure controls are added to layout
- Check that layout is added to scene
- Verify scene is set on stage
- Check that `show()` is called on stage

### Issue: "Events not working"
- **Solution**: Verify event handlers are set correctly
- Check lambda expression syntax
- Ensure controls are not disabled

### Issue: "Styling not applied"
- **Solution**: Check CSS property names (use `-fx-` prefix)
- Verify style string syntax
- Check for typos in property names

### Issue: "Layout not working as expected"
- **Solution**: Understand layout behavior (VBox stacks vertically, etc.)
- Check padding and spacing settings
- Use appropriate layout for your needs

## Next Steps

After mastering this lesson, consider:

1. **FXML and Scene Builder**
   - Visual UI design
   - Separate UI from code
   - Professional workflow

2. **CSS Styling**
   - External stylesheets
   - Themes and skins
   - Responsive styling

3. **Advanced Controls**
   - TableView for data tables
   - TreeView for hierarchical data
   - WebView for HTML content
   - MediaView for video

4. **Charts and Visualization**
   - LineChart, BarChart, PieChart
   - Data visualization
   - Interactive charts

5. **Animations**
   - Transitions (Fade, Translate, Rotate)
   - Timeline for complex animations
   - Animation effects

6. **3D Graphics**
   - 3D shapes and scenes
   - Camera and lighting
   - 3D transformations

7. **Concurrency**
   - Background tasks with Task and Service
   - Platform.runLater() for UI updates
   - Multithreading in JavaFX

8. **Custom Controls**
   - Create reusable UI components
   - Extend existing controls
   - Custom painting

## Additional Resources

### JavaFX Documentation
- [JavaFX Official Documentation](https://openjfx.io/)
- [JavaFX API Documentation](https://openjfx.io/javadoc/21/)
- [JavaFX Tutorials](https://openjfx.io/openjfx-docs/)

### Learning Resources
- [JavaFX Tutorial by Code.makery](https://code.makery.ch/library/javafx-tutorial/)
- [JavaFX Examples](https://github.com/jewelsea/javafx-examples)

### Tools
- [Scene Builder](https://gluonhq.com/products/scene-builder/) - Visual UI designer
- [JavaFX CSS Reference](https://openjfx.io/javadoc/21/javafx.graphics/javafx/scene/doc-files/cssref.html)

### Concepts to Explore
- **MVVM Pattern**: Model-View-ViewModel architecture
- **Binding**: Property binding for reactive UI
- **Properties**: Observable properties
- **Concurrency**: Background tasks and UI updates
- **Custom Controls**: Creating reusable components
- **Internationalization**: Multi-language support

## Notes

- JavaFX is now a separate module (not included in JDK 11+)
- Requires explicit dependencies in build files
- Module system requires `--add-modules` flag
- CSS styling is powerful and flexible
- FXML allows visual UI design
- JavaFX provides modern, attractive default styling
- Hardware acceleration provides smooth performance
- Cross-platform with consistent look across platforms
- Active development and community support
