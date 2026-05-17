# ShadowFox Calculator

A feature-rich calculator built with Java as part of the
ShadowFox Java Internship - Task 1 (All Tiers Completed).

## Features

### Tier 1 - Console Calculator
- Basic arithmetic (Add, Subtract, Multiply, Divide)
- Scientific calculations (Square Root, Power)
- Unit Converter (Temperature & Currency)
- BigDecimal precision (no floating point errors)
- Full exception handling

### Tier 1 - Swing GUI Calculator
- Beautiful dark theme UI
- All arithmetic operations
- Scientific buttons (√, x²)
- Unit converter buttons (°C→°F, °F→°C, $→₹, ₹→$)
- Color coded buttons

### Tier 2 - Expression Parser (BODMAS)
- Type full expressions like `5+3*2` or `(5+3)*2`
- Correct operator precedence (BODMAS/PEMDAS)
- Supports brackets and nested expressions
- Built using Shunting Yard Algorithm with Stack

## How to Run

### Console Version
1. Open project in IntelliJ IDEA
2. Run `Main.java`

### GUI Version (with Expression Parser)
1. Open project in IntelliJ IDEA
2. Run `CalculatorGUI.java`
3. Use buttons for basic calculations
4. Type expressions in top field and click `= Evaluate`

## Expression Examples
| Expression | Result |
|-----------|--------|
| `5+3*2` | `11.0` |
| `(5+3)*2` | `16.0` |
| `10+2*5-(3+1)` | `16.0` |

## Technologies Used
- Java 25
- Swing (GUI)
- BigDecimal (Precision)
- Stack (Shunting Yard Algorithm)

## Project Structure
src/
├── Main.java              → Console Calculator
├── CalculatorGUI.java     → Swing GUI Calculator
└── ExpressionParser.java  → BODMAS Expression Parser

## Author
Sai Kumar - ShadowFox Java Internship 2026