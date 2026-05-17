import java.math.BigDecimal;
import java.math.MathContext;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n===== ShadowFox Calculator =====");
            System.out.println("1. Addition");
            System.out.println("2. Subtraction");
            System.out.println("3. Multiplication");
            System.out.println("4. Division");
            System.out.println("5. Square Root");
            System.out.println("6. Power");
            System.out.println("7. Unit Converter");
            System.out.println("0. Exit");
            System.out.print("Choose: ");

            try {
                int choice = sc.nextInt();

                if (choice == 0) {
                    running = false;
                    System.out.println("Goodbye!");

                } else if (choice >= 1 && choice <= 4) {
                    System.out.print("Enter first number: ");
                    BigDecimal a = sc.nextBigDecimal();
                    System.out.print("Enter second number: ");
                    BigDecimal b = sc.nextBigDecimal();

                    switch (choice) {
                        case 1 -> System.out.println("Result: " + a.add(b));
                        case 2 -> System.out.println("Result: " + a.subtract(b));
                        case 3 -> System.out.println("Result: " + a.multiply(b));
                        case 4 -> {
                            if (b.compareTo(BigDecimal.ZERO) == 0)
                                System.out.println("Error: Cannot divide by zero!");
                            else
                                System.out.println("Result: " + a.divide(b, MathContext.DECIMAL64));
                        }
                    }

                } else if (choice == 5) {
                    System.out.print("Enter number: ");
                    double num = sc.nextDouble();
                    if (num < 0)
                        System.out.println("Error: Cannot sqrt a negative number!");
                    else
                        System.out.println("Result: " + Math.sqrt(num));

                } else if (choice == 6) {
                    System.out.print("Enter base: ");
                    double base = sc.nextDouble();
                    System.out.print("Enter exponent: ");
                    double exp = sc.nextDouble();
                    System.out.println("Result: " + Math.pow(base, exp));

                } else if (choice == 7) {
                    System.out.println("\n-- Unit Converter --");
                    System.out.println("1. Celsius to Fahrenheit");
                    System.out.println("2. Fahrenheit to Celsius");
                    System.out.println("3. USD to INR");
                    System.out.println("4. INR to USD");
                    System.out.print("Choose converter: ");
                    int convChoice = sc.nextInt();
                    System.out.print("Enter value: ");
                    double val = sc.nextDouble();

                    switch (convChoice) {
                        case 1 -> System.out.println("Result: " + ((val * 9.0/5) + 32) + " °F");
                        case 2 -> System.out.println("Result: " + ((val - 32) * 5.0/9) + " °C");
                        case 3 -> System.out.println("Result: Rs." + (val * 83.5));
                        case 4 -> System.out.println("Result: $" + (val / 83.5));
                        default -> System.out.println("Invalid choice!");
                    }

                } else {
                    System.out.println("Invalid choice!");
                }

            } catch (InputMismatchException e) {
                System.out.println("Error: Please enter a valid number!");
                sc.nextLine();
            }
        }
    }
}7