# Lesson 14: Java Swing GUI

This lesson introduces building graphical user interfaces (GUIs) in Java using Swing. Swing is part of the Java Standard Library and allows you to create desktop applications with windows, buttons, text fields, and more.

## What You'll Learn
- Creating a basic Swing window (JFrame)
- Adding UI elements like buttons (JButton), text fields (JTextField), and labels (JLabel)
- Using layouts to arrange components (e.g., BorderLayout, FlowLayout)
- Handling events with listeners (e.g., ActionListener for button clicks)
- Building a simple interactive app (a basic form with input and output)

## Running the Example
1. Navigate to this lesson directory.
2. Run `./gradlew runSwingApp` (or `gradle runSwingApp` if using the Kotlin DSL).
3. A GUI window will open. Interact with the elements to see layouts and events in action.

## Exercises
- Modify the layout from BorderLayout to GridLayout and observe the changes.
- Add a new button that clears the text field.
- Extend the app to include a dropdown (JComboBox) with options.
- Experiment with other layouts like BoxLayout or CardLayout.

## Key Concepts
- **JFrame**: The main window.
- **JPanel**: A container for grouping components.
- **Layouts**: Managers that control component positioning (e.g., BorderLayout places components in north/south/east/west/center).
- **Events**: Respond to user actions like clicks using listeners.
