//Name: Njaji Sibona Samson
//Reg.no: 23/05541
//BSD
// Github link: https://github.com/samsonnjaji/NumberEvaluator
// BSD 2304:ADVANCED JAVA PROGRAMMING

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

public class NumberEvaluator extends JFrame implements ActionListener, KeyListener {
    private JTextField inputField;
    private JButton checkButton;
    private JButton clearButton;
    private JLabel resultLabel;
    private JLabel instructionLabel;
    private JLabel headerLabel;
    private JScrollPane scrollPane;
    private JPanel mainPanel;

    public NumberEvaluator() {
        setupInterface();
        createLayout();
        wireEvents();
        initializeWindow();
    }

    private void setupInterface() {
        headerLabel = new JLabel("Number Evaluator Tool");
        instructionLabel = new JLabel("Enter any number below:");
        inputField = new JTextField(22);
        checkButton = new JButton("Check Number");
        clearButton = new JButton("Clear");
        resultLabel = new JLabel("Ready to check your number...");

        headerLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        instructionLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        inputField.setFont(new Font("Courier New", Font.PLAIN, 16));
        checkButton.setFont(new Font("Arial", Font.BOLD, 13));
        clearButton.setFont(new Font("Arial", Font.BOLD, 13));
        resultLabel.setFont(new Font("Verdana", Font.ITALIC, 14));

        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        instructionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);

        headerLabel.setForeground(new Color(51, 102, 153));
        instructionLabel.setForeground(new Color(102, 102, 102));
        resultLabel.setForeground(new Color(85, 85, 85));
        checkButton.setBackground(new Color(60, 179, 113));
        checkButton.setForeground(Color.WHITE);
        checkButton.setOpaque(true);
        checkButton.setBorderPainted(false);
        checkButton.setFocusPainted(false);

        clearButton.setBackground(new Color(220, 53, 69));
        clearButton.setForeground(Color.WHITE);
        clearButton.setOpaque(true);
        clearButton.setBorderPainted(false);
        clearButton.setFocusPainted(false);

        inputField.setBackground(new Color(250, 250, 250));
        inputField.setBorder(new LineBorder(new Color(204, 204, 204), 2));
        inputField.setHorizontalAlignment(SwingConstants.CENTER);
    }

    private void createLayout() {
        mainPanel = new JPanel();
        mainPanel.setBackground(new Color(245, 245, 245));
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = 0; constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(25, 30, 10, 30);
        constraints.anchor = GridBagConstraints.CENTER;
        mainPanel.add(headerLabel, constraints);

        constraints.gridx = 0; constraints.gridy = 1;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(5, 30, 20, 30);
        mainPanel.add(instructionLabel, constraints);

        constraints.gridx = 0; constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 40, 20, 40);
        mainPanel.add(inputField, constraints);

        constraints.gridx = 0; constraints.gridy = 3;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 40, 10, 15);
        constraints.ipadx = 10; constraints.ipady = 8;
        mainPanel.add(checkButton, constraints);

        constraints.gridx = 1; constraints.gridy = 3;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 15, 10, 40);
        constraints.ipadx = 10; constraints.ipady = 8;
        mainPanel.add(clearButton, constraints);

        constraints.gridx = 0; constraints.gridy = 4;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(20, 40, 25, 40);
        constraints.ipadx = 0; constraints.ipady = 0;
        mainPanel.add(resultLabel, constraints);

        scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(null);
        add(scrollPane);
    }

    private void wireEvents() {
        checkButton.addActionListener(this);
        clearButton.addActionListener(this);
        inputField.addKeyListener(this);

        checkButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                checkButton.setBackground(new Color(72, 191, 125));
            }
            public void mouseExited(MouseEvent evt) {
                checkButton.setBackground(new Color(60, 179, 113));
            }
        });

        clearButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                clearButton.setBackground(new Color(232, 65, 81));
            }
            public void mouseExited(MouseEvent evt) {
                clearButton.setBackground(new Color(220, 53, 69));
            }
        });
    }

    private void initializeWindow() {
        setTitle("Number Evaluator - Advanced Java Programming");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(480, 350);
        setResizable(true);
        setMinimumSize(new Dimension(420, 320));
        setLocationRelativeTo(null);
        inputField.requestFocusInWindow();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == checkButton) {
            checkTheNumber();
        } else if (e.getSource() == clearButton) {
            resetEverything();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            checkTheNumber();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    private void checkTheNumber() {
        String userInput = inputField.getText().trim();

        if (userInput.isEmpty()) {
            showResult("Please enter a value to check!", new Color(204, 0, 0));
            return;
        }

        try {
            double num = Double.parseDouble(userInput);
            String message;
            Color messageColor;

            if (num > 0) {
                message = userInput + " is POSITIVE";
                messageColor = new Color(34, 139, 34);
            } else if (num < 0) {
                message = userInput + " is NEGATIVE";
                messageColor = new Color(178, 34, 34);
            } else {
                message = "The number is ZERO";
                messageColor = new Color(0, 100, 200);
            }

            showResult(message, messageColor);

        } catch (NumberFormatException ex) {
            showResult("Invalid input! Please enter a valid number.", new Color(204, 0, 0));
        }

        mainPanel.revalidate();
        mainPanel.repaint();
        scrollPane.revalidate();
    }

    private void showResult(String text, Color textColor) {
        resultLabel.setText("<html><center><b>" + text + "</b></center></html>");
        resultLabel.setForeground(textColor);

        resultLabel.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(textColor, 1),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
    }

    private void resetEverything() {
        inputField.setText("");
        resultLabel.setText("Ready to check your number...");
        resultLabel.setForeground(new Color(85, 85, 85));
        resultLabel.setBorder(null);
        inputField.requestFocusInWindow();

        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new NumberEvaluator().setVisible(true);
            }
        });
    }
}