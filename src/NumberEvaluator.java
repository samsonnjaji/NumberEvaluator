//Name: Njaji Sibona Samson
//Reg.no: 23/05541
//BSD
                             // BSD 2304:ADVANCED JAVA PROGRAMMING


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NumberEvaluator extends JFrame implements ActionListener, KeyListener {
    private JTextField inputField;
    private JButton checkButton;
    private JButton clearButton;
    private JLabel resultLabel;
    private JLabel instructionLabel;
    private JScrollPane scrollPane;
    private JPanel mainPanel;

    public NumberEvaluator() {
        initializeComponents();
        layoutComponents();
        addEventListeners();
        configureFrame();
    }

    private void initializeComponents() {
        instructionLabel = new JLabel("Enter a number to evaluate:");
        inputField = new JTextField(20);
        checkButton = new JButton("Check Number");
        clearButton = new JButton("Clear");
        resultLabel = new JLabel("Result will appear here");

        inputField.setFont(new Font("Arial", Font.PLAIN, 16));
        checkButton.setFont(new Font("Arial", Font.BOLD, 14));
        clearButton.setFont(new Font("Arial", Font.BOLD, 14));
        resultLabel.setFont(new Font("Arial", Font.ITALIC, 15));
        instructionLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);

        checkButton.setBackground(new Color(34, 139, 34));
        checkButton.setForeground(Color.WHITE);
        checkButton.setOpaque(true);
        checkButton.setBorderPainted(false);
        checkButton.setFocusPainted(false);

        clearButton.setBackground(new Color(255, 69, 0));
        clearButton.setForeground(Color.WHITE);
        clearButton.setOpaque(true);
        clearButton.setBorderPainted(false);
        clearButton.setFocusPainted(false);

        resultLabel.setForeground(new Color(25, 25, 112));
    }

    private void layoutComponents() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 20, 15, 20);
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(instructionLabel, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 20, 15, 20);
        mainPanel.add(inputField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 20, 15, 10);
        mainPanel.add(checkButton, gbc);

        gbc.gridx = 1; gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 15, 20);
        mainPanel.add(clearButton, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(15, 20, 20, 20);
        mainPanel.add(resultLabel, gbc);

        scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane);
    }

    private void addEventListeners() {
        checkButton.addActionListener(this);
        clearButton.addActionListener(this);
        inputField.addKeyListener(this);
    }

    private void configureFrame() {
        setTitle("Number Evaluator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 280);
        setResizable(true);
        setMinimumSize(new Dimension(350, 250));
        setLocationRelativeTo(null);
        inputField.requestFocusInWindow();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == checkButton) {
            evaluateNumber();
        } else if (e.getSource() == clearButton) {
            clearFields();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            evaluateNumber();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    private void evaluateNumber() {
        String input = inputField.getText().trim();

        if (input.isEmpty()) {
            displayResult("Please enter a value!", Color.RED);
            return;
        }

        try {
            double number = Double.parseDouble(input);
            String result;
            Color color;

            if (number > 0) {
                result = input + " is a POSITIVE number\nGreat! You entered a positive value.";
                color = new Color(0, 128, 0);
            } else if (number < 0) {
                result = input + " is a NEGATIVE number\nThis value is less than zero.";
                color = new Color(220, 20, 60);
            } else {
                result = "The number is ZERO\nThis is neither positive nor negative.";
                color = new Color(25, 25, 112);
            }

            displayResult(result, color);

        } catch (NumberFormatException e) {
            displayResult("Error: '" + input + "' is not a valid number\nPlease enter numbers only (e.g., 123, -45, 3.14,0 ,+9,-9)", Color.RED);
        }

        mainPanel.revalidate();
        mainPanel.repaint();
        scrollPane.revalidate();
    }

    private void displayResult(String message, Color color) {
        resultLabel.setText("<html><div style='text-align: center;'>" +
                message.replace("\n", "<br>") + "</div></html>");
        resultLabel.setForeground(color);
    }

    private void clearFields() {
        inputField.setText("");
        resultLabel.setText("Result will appear here");
        resultLabel.setForeground(new Color(25, 25, 112));
        inputField.requestFocusInWindow();

        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                new NumberEvaluator().setVisible(true);
            }
        });
    }
}