import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.math.MathContext;

public class CalculatorGUI {

    private JFrame frame;
    private JTextField display;
    private JTextField expressionField;
    private String currentInput = "";
    private String operator = "";
    private BigDecimal firstNumber = BigDecimal.ZERO;
    private boolean newInput = true;
    private ExpressionParser parser = new ExpressionParser();

    public CalculatorGUI() {
        frame = new JFrame("ShadowFox Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 700);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(Color.decode("#1e1e2e"));

        // Top panel with expression input + display
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.setBackground(Color.decode("#1e1e2e"));

        // Expression input field
        expressionField = new JTextField("Type expression e.g. 5+3*2");
        expressionField.setFont(new Font("Arial", Font.PLAIN, 14));
        expressionField.setBackground(Color.decode("#181825"));
        expressionField.setForeground(Color.GRAY);
        expressionField.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));

        // Evaluate button
        JButton evalBtn = new JButton("= Evaluate");
        evalBtn.setBackground(Color.decode("#a6e3a1"));
        evalBtn.setForeground(Color.BLACK);
        evalBtn.setFont(new Font("Arial", Font.BOLD, 13));
        evalBtn.setFocusPainted(false);
        evalBtn.setBorderPainted(false);
        evalBtn.addActionListener(e -> evaluateExpression());

        // Clear placeholder on click
        expressionField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent e) {
                if (expressionField.getText().equals("Type expression e.g. 5+3*2")) {
                    expressionField.setText("");
                    expressionField.setForeground(Color.WHITE);
                }
            }
        });

        JPanel exprPanel = new JPanel(new BorderLayout());
        exprPanel.setBackground(Color.decode("#1e1e2e"));
        exprPanel.add(expressionField, BorderLayout.CENTER);
        exprPanel.add(evalBtn, BorderLayout.EAST);

        // Main display
        display = new JTextField("0");
        display.setFont(new Font("Arial", Font.BOLD, 36));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        display.setBackground(Color.decode("#2e2e3e"));
        display.setForeground(Color.WHITE);
        display.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        topPanel.add(exprPanel, BorderLayout.NORTH);
        topPanel.add(display, BorderLayout.CENTER);
        frame.add(topPanel, BorderLayout.NORTH);

        // Button panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 4, 8, 8));
        panel.setBackground(Color.decode("#1e1e2e"));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] buttons = {
                "C", "±", "%", "÷",
                "7", "8", "9", "×",
                "4", "5", "6", "-",
                "1", "2", "3", "+",
                "√", "x²", "0", "=",
                "°C→°F", "°F→°C", "$→₹", "₹→$"
        };

        for (String label : buttons) {
            JButton btn = createButton(label);
            panel.add(btn);
        }

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void evaluateExpression() {
        try {
            String expr = expressionField.getText().trim();
            double result = parser.evaluate(expr);
            display.setText(String.valueOf(result));
            currentInput = String.valueOf(result);
        } catch (Exception ex) {
            display.setText("Error: Invalid Expression");
        }
    }

    private JButton createButton(String label) {
        JButton btn = new JButton(label);
        btn.setFont(new Font("Arial", Font.BOLD, 16));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        if (label.equals("=")) {
            btn.setBackground(Color.decode("#f38ba8"));
            btn.setForeground(Color.WHITE);
        } else if (label.equals("C")) {
            btn.setBackground(Color.decode("#a6e3a1"));
            btn.setForeground(Color.BLACK);
        } else if ("÷×-+".contains(label)) {
            btn.setBackground(Color.decode("#fab387"));
            btn.setForeground(Color.WHITE);
        } else if (label.contains("°") || label.contains("$") || label.contains("₹")) {
            btn.setBackground(Color.decode("#89b4fa"));
            btn.setForeground(Color.WHITE);
        } else {
            btn.setBackground(Color.decode("#313244"));
            btn.setForeground(Color.WHITE);
        }

        btn.addActionListener(e -> handleButton(label));
        return btn;
    }

    private void handleButton(String label) {
        try {
            switch (label) {
                case "C" -> {
                    currentInput = "";
                    firstNumber = BigDecimal.ZERO;
                    operator = "";
                    display.setText("0");
                    expressionField.setText("Type expression e.g. 5+3*2");
                    expressionField.setForeground(Color.GRAY);
                    newInput = true;
                }
                case "÷", "×", "-", "+" -> {
                    if (!currentInput.isEmpty()) {
                        firstNumber = new BigDecimal(currentInput);
                        operator = label;
                        newInput = true;
                        display.setText(display.getText() + " " + label);
                    }
                }
                case "=" -> {
                    if (!operator.isEmpty() && !currentInput.isEmpty()) {
                        BigDecimal second = new BigDecimal(currentInput);
                        BigDecimal result = switch (operator) {
                            case "+" -> firstNumber.add(second);
                            case "-" -> firstNumber.subtract(second);
                            case "×" -> firstNumber.multiply(second);
                            case "÷" -> second.compareTo(BigDecimal.ZERO) == 0
                                    ? null : firstNumber.divide(second, MathContext.DECIMAL64);
                            default -> null;
                        };
                        if (result == null) {
                            display.setText("Error: Div by 0");
                        } else {
                            display.setText(result.stripTrailingZeros().toPlainString());
                            currentInput = result.toPlainString();
                        }
                        operator = "";
                        newInput = true;
                    }
                }
                case "√" -> {
                    if (!currentInput.isEmpty()) {
                        double val = Double.parseDouble(currentInput);
                        if (val < 0) display.setText("Error");
                        else {
                            String res = String.valueOf(Math.sqrt(val));
                            display.setText(res);
                            currentInput = res;
                        }
                    }
                }
                case "x²" -> {
                    if (!currentInput.isEmpty()) {
                        BigDecimal val = new BigDecimal(currentInput);
                        String res = val.multiply(val).toPlainString();
                        display.setText(res);
                        currentInput = res;
                    }
                }
                case "±" -> {
                    if (!currentInput.isEmpty()) {
                        BigDecimal val = new BigDecimal(currentInput).negate();
                        currentInput = val.toPlainString();
                        display.setText(currentInput);
                    }
                }
                case "%" -> {
                    if (!currentInput.isEmpty()) {
                        BigDecimal val = new BigDecimal(currentInput)
                                .divide(BigDecimal.valueOf(100), MathContext.DECIMAL64);
                        currentInput = val.toPlainString();
                        display.setText(currentInput);
                    }
                }
                case "°C→°F" -> {
                    if (!currentInput.isEmpty()) {
                        double val = Double.parseDouble(currentInput);
                        double res = (val * 9.0 / 5) + 32;
                        display.setText(val + "°C = " + res + "°F");
                        currentInput = "";
                    }
                }
                case "°F→°C" -> {
                    if (!currentInput.isEmpty()) {
                        double val = Double.parseDouble(currentInput);
                        double res = (val - 32) * 5.0 / 9;
                        display.setText(val + "°F = " + res + "°C");
                        currentInput = "";
                    }
                }
                case "$→₹" -> {
                    if (!currentInput.isEmpty()) {
                        double val = Double.parseDouble(currentInput);
                        display.setText("$" + val + " = Rs." + (val * 83.5));
                        currentInput = "";
                    }
                }
                case "₹→$" -> {
                    if (!currentInput.isEmpty()) {
                        double val = Double.parseDouble(currentInput);
                        display.setText("Rs." + val + " = $" + (val / 83.5));
                        currentInput = "";
                    }
                }
                default -> {
                    if (newInput) {
                        currentInput = label;
                        newInput = false;
                    } else {
                        currentInput += label;
                    }
                    display.setText(currentInput);
                }
            }
        } catch (Exception ex) {
            display.setText("Error");
            currentInput = "";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CalculatorGUI::new);
    }
}