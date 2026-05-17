# My Learnings

## Hardest Bug
Using `double` gave `0.1 + 0.2 = 0.30000000000000004`

## How I Fixed It
Switched to `BigDecimal` which gives exact precision: `0.3`

## What I Learned
- BigDecimal for financial/precise calculations
- Exception handling with try-catch
- Java Swing GUI with ActionListener
- MVC separation between logic and UI