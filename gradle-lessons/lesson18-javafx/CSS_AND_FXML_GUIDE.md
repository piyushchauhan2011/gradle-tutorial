# CSS and FXML Guide for JavaFX Lesson 18

## Quick Start

### Running CSS Example
```bash
# Update build.gradle mainClass to:
application {
    mainClass = 'com.example.javafx.HelloWorldCssApp'
}
./gradlew -p lesson18-javafx run
```

### Running FXML Example
```bash
# Update build.gradle mainClass to:
application {
    mainClass = 'com.example.javafx.HelloWorldFxmlApp'
}
./gradlew -p lesson18-javafx run
```

## Files Created

### CSS Files
- `src/main/resources/com/example/javafx/styles.css` - General stylesheet with buttons, labels, containers
- `src/main/resources/com/example/javafx/calculator.css` - Calculator-specific styles

### FXML Files
- `src/main/resources/com/example/javafx/HelloWorld.fxml` - Simple HelloWorld layout
- `src/main/resources/com/example/javafx/TodoList.fxml` - Complex TodoList layout

### Controllers
- `src/main/java/com/example/javafx/HelloWorldController.java` - Controller for HelloWorld.fxml
- `src/main/java/com/example/javafx/TodoListController.java` - Controller for TodoList.fxml

### Applications
- `HelloWorldCssApp.java` - Demonstrates external CSS styling
- `HelloWorldFxmlApp.java` - Demonstrates FXML loading
- `TodoListFxmlApp.java` - Complex FXML example

## Key Concepts

### CSS Styling
1. **Load CSS**: `scene.getStylesheets().add(resource.toExternalForm())`
2. **Apply Classes**: `node.getStyleClass().add("class-name")`
3. **Set IDs**: `node.setId("unique-id")`
4. **Pseudo-classes**: `:hover`, `:pressed`, `:focused`, `:selected`

### FXML Layouts
1. **Create FXML**: XML file defining UI structure
2. **Create Controller**: Java class with @FXML annotations
3. **Link Elements**: Use `fx:id` in FXML, `@FXML` in controller
4. **Handle Events**: `onAction="#methodName"` in FXML

## Learning Path

1. **Start with CSS**: Modify `HelloWorldCssApp` to see CSS in action
2. **Explore FXML**: Run `HelloWorldFxmlApp` to see FXML structure
3. **Combine Both**: `TodoListFxmlApp` shows CSS + FXML together
4. **Read Examples**: Check the FXML and CSS files to understand syntax
5. **Modify Styles**: Change CSS colors, fonts, effects
6. **Create Your Own**: Build a new FXML file for your UI

## Benefits

### CSS Benefits
- ✅ Separation of style from code
- ✅ Easy to maintain and update
- ✅ Reusable styles across applications
- ✅ Designer-friendly (familiar CSS syntax)
- ✅ Pseudo-classes for interactive states

### FXML Benefits
- ✅ Separation of UI structure from logic
- ✅ Visual design tools (Scene Builder)
- ✅ Easier collaboration between designers and developers
- ✅ Version control friendly
- ✅ Reusable UI components
