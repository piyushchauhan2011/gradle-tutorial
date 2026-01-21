import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingApp {
    public static void main(String[] args) {
        // Create the main frame (window)
        JFrame frame = new JFrame("Swing GUI Lesson");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Set layout for the frame (BorderLayout: north, south, east, west, center)
        frame.setLayout(new BorderLayout());

        // Create a label (UI element)
        JLabel label = new JLabel("Enter your name:", SwingConstants.CENTER);
        frame.add(label, BorderLayout.NORTH);

        // Create a text field (input element)
        JTextField textField = new JTextField();
        frame.add(textField, BorderLayout.CENTER);

        // Create a panel for buttons (using FlowLayout for horizontal arrangement)
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        // Create buttons (interactive elements)
        JButton greetButton = new JButton("Greet");
        JButton clearButton = new JButton("Clear");

        // Add event listeners (handling events)
        greetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = textField.getText();
                JOptionPane.showMessageDialog(frame, "Hello, " + name + "!");
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText("");
            }
        });

        // Add buttons to the panel
        buttonPanel.add(greetButton);
        buttonPanel.add(clearButton);

        // Add the button panel to the frame
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Make the frame visible
        frame.setVisible(true);
    }
}
