import java.util.Stack;

public class ExpressionParser {

    public double evaluate(String expression) {
        expression = expression.replaceAll("\\s+", "");
        Stack<Double> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            // If number
            if (Character.isDigit(c) || c == '.') {
                StringBuilder sb = new StringBuilder();
                while (i < expression.length() &&
                        (Character.isDigit(expression.charAt(i)) ||
                                expression.charAt(i) == '.')) {
                    sb.append(expression.charAt(i++));
                }
                i--;
                numbers.push(Double.parseDouble(sb.toString()));
            }

            // If opening bracket
            else if (c == '(') {
                operators.push(c);
            }

            // If closing bracket
            else if (c == ')') {
                while (operators.peek() != '(') {
                    numbers.push(applyOperator(
                            operators.pop(),
                            numbers.pop(),
                            numbers.pop()
                    ));
                }
                operators.pop();
            }

            // If operator
            else if (c == '+' || c == '-' || c == '*' || c == '/') {
                while (!operators.isEmpty() &&
                        hasPrecedence(c, operators.peek())) {
                    numbers.push(applyOperator(
                            operators.pop(),
                            numbers.pop(),
                            numbers.pop()
                    ));
                }
                operators.push(c);
            }
        }

        while (!operators.isEmpty()) {
            numbers.push(applyOperator(
                    operators.pop(),
                    numbers.pop(),
                    numbers.pop()
            ));
        }

        return numbers.pop();
    }

    private boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')') return false;
        if ((op1 == '*' || op1 == '/') &&
                (op2 == '+' || op2 == '-')) return false;
        return true;
    }

    private double applyOperator(char op, double b, double a) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/':
                if (b == 0) throw new ArithmeticException("Divide by zero!");
                return a / b;
        }
        return 0;
    }
}