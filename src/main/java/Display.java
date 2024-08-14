import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Display extends JFrame {
    JPanel calculationPanel;
    JPanel numberPanel;
    JPanel operationsPanel;
    JLabel previousNumberDisplay;
    JLabel calculationDisplay;
    Border lineBorder;
    JTextField numberDisplay;
    String[] operations;
    double number1;
    double number2;
    String operation;
    Operations functionality;
    
    void createCalculationPanel() {
        calculationPanel = new JPanel();
        calculationPanel.setLayout(new GridLayout(3, 1));
        calculationPanel.setBorder(lineBorder);

        // Create the display for the current number
        Font font = new Font("Arial", Font.PLAIN, 24);
        numberDisplay = new JTextField();
        numberDisplay.setPreferredSize(new Dimension(200, 50));
        numberDisplay.setFont(font);

        // Create the display for the previous number and calculation
        previousNumberDisplay = new JLabel();
        calculationDisplay = new JLabel();
        
        // Add the displays to the panel
        calculationPanel.add(numberDisplay);
        calculationPanel.add(previousNumberDisplay);
        calculationPanel.add(calculationDisplay);
    }

    void createNumberPanel() {
        numberPanel = new JPanel();
        numberPanel.setLayout(new GridLayout(4, 3));
        numberPanel.setBorder(lineBorder);

        // Create the number and operation buttons
        String[] buttonLabels = {"7", "8", "9",
                                 "4", "5", "6",
                                 "1", "2", "3",
                                 ".", "0", "<-"};

        for (String label : buttonLabels) {
            JButton button = new JButton(label);

            // Edit the display based on user input
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JButton origin = (JButton) e.getSource();
                    String buttonLabel = origin.getText();

                    if (buttonLabel.equals("<-")) {
                        String previousNumber = numberDisplay.getText();
                        previousNumber = previousNumber.substring(0, previousNumber.length() - 1);
                        numberDisplay.setText(previousNumber);
                    }
                    else {
                        String currentNumber = numberDisplay.getText();
                        currentNumber += buttonLabel;
                        numberDisplay.setText(currentNumber);
                    }
                }
            });
            numberPanel.add(button);
        }
    }

    void createOperationsPanel() {
        operationsPanel = new JPanel();
        operationsPanel.setLayout(new GridLayout(5, 1));
        operationsPanel.setBorder(lineBorder);

        // Create the operation buttons
        String[] operations = {"+", "-", "x", "÷", "="};
        for (String label : operations) {
            JButton button = new JButton(label);

            // Use the Operations class to perform the calculation
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JButton origin = (JButton) e.getSource();
                    String buttonLabel = origin.getText();
                    String currentNumber = numberDisplay.getText();
                    numberDisplay.setText("");
                    previousNumberDisplay.setText(currentNumber);

                    // If the user presses the equals button, perform the calculation
                    if (buttonLabel.equals("=")) {
                        number2 = Double.parseDouble(currentNumber);
                        double answer = functionality.performCalculation(number1, number2, operation);
                        numberDisplay.setText(String.valueOf(answer));
                        String calculations = number1 + operation + number2 + "=" + answer;
                        calculationDisplay.setText(calculations);
                    }
                    else {
                        number1 = Double.parseDouble(currentNumber);
                        operation = buttonLabel;
                    }
                }
            });
            operationsPanel.add(button);
        }
    }
    
    public Display() {
        // Set up the JFrame
        this.setSize(300, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Calculator");
        this.setLayout(new BorderLayout());

        // Set up the panels
        lineBorder = BorderFactory.createLineBorder(Color.GRAY, 2);
        functionality = new Operations();
        createCalculationPanel();
        createNumberPanel();
        createOperationsPanel();

        // Add the panels to the JFrame
        this.add(calculationPanel, BorderLayout.NORTH);
        this.add(numberPanel, BorderLayout.CENTER);
        this.add(operationsPanel, BorderLayout.EAST);
        this.setVisible(true);
    }
}