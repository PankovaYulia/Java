package org.example;

public class Main {
    public static void main(String[] args) {
        ExpressionCalculator calculator = new ExpressionCalculator();

        // Пример 1: Простое вычисление выражения с числами
        double result1 = calculator.evaluateExpression("2 + 3 * 4");
        System.out.println("2 + 3 * 4 = " + result1);  // Выведет: 2 + 3 * 4 = 14.0

        // Пример 2: Использование скобок
        double result2 = calculator.evaluateExpression("(2 + 3) * 4");
        System.out.println("(2 + 3) * 4 = " + result2);  // Выведет: (2 + 3) * 4 = 20.0

        // Пример 3: Использование переменных
        calculator.addVariable("x", 5);
        calculator.addVariable("y", 2);
        double result3 = calculator.evaluateExpression("x^2 + y*3");
        System.out.println("x^2 + y*3 (где x=5, y=2) = " + result3);  // Выведет: x^2 + y*3 (где x=5, y=2) = 31.0

    }
}