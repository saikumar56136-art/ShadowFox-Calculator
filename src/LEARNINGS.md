# Learnings - ShadowFox Calculator

## Tier 1 Learnings

### Hardest Bug
Using `double` gave wrong results like
`0.1 + 0.2 = 0.30000000000000004`.

### How I Fixed It
Switched to `BigDecimal` which handles decimal
precision correctly. `0.1 + 0.2 = 0.3` ✅

### What I Learned
- BigDecimal for precise calculations
- Exception handling with try-catch
- Swing GUI with ActionListener
- Event-driven programming
- MVC concept basics
- Git and GitHub workflow

## Tier 2 Learnings

### Hardest Bug
Expression `5+3*2` was giving `16` instead of `11`
because operations were evaluated left to right
without respecting operator precedence.

### How I Fixed It
Implemented the **Shunting Yard Algorithm** using
two Stacks — one for numbers, one for operators.
The algorithm checks operator precedence before
pushing to the stack, ensuring BODMAS is followed.

### What I Learned
- Shunting Yard Algorithm
- Stack data structure in Java
- Operator precedence logic
- Parsing strings character by character
- Connecting separate classes in a GUI app