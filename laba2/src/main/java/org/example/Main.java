package org.example;

public class Main {
    public static void main(String[] args) {
        ExpressionCalculator calculator = new ExpressionCalculator();

        double result = calculator.evaluateExpression("2 + 3 * 4");

        System.out.println("Результат: " + result);  // Выведет 14.0
    }
}