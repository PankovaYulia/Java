package org.example;

public class Main {
    public static void main(String[] args) {
        ExpressionCalculator calculator = new ExpressionCalculator();

        // 1. Простые вычисления с числами
        System.out.println("Базовые операции:");
        System.out.println("2 + 3 * 4 = " + calculator.evaluateExpression("2 + 3 * 4")); // 14.0
        System.out.println("(2 + 3) * 4 = " + calculator.evaluateExpression("(2 + 3) * 4")); // 20.0
        System.out.println("3 ^ 2 = " + calculator.evaluateExpression("3^2")); // 9.0
        System.out.println("10 / 4 = " + calculator.evaluateExpression("10/4")); // 2.5

        // 2. Работа с переменными
        System.out.println("\nРабота с переменными:");
        calculator.addVariable("x", 5.0);
        calculator.addVariable("y", 2.0);
        calculator.addVariable("pi", 3.14159);

        System.out.println("x = 5.0, y = 2.0, pi ≈ 3.14159");
        System.out.println("x + y = " + calculator.evaluateExpression("x + y")); // 7.0
        System.out.println("x * y - 3 = " + calculator.evaluateExpression("x * y - 3")); // 7.0
        System.out.println("(x + y) ^ 2 = " + calculator.evaluateExpression("(x + y)^2")); // 49.0

        // 3. Математические функции
        System.out.println("\nМатематические функции:");
        System.out.println("sqrt(x) = " + calculator.evaluateExpression("sqrt(x)")); // √5 ≈ 2.236
        System.out.println("sin(30) = " + calculator.evaluateExpression("sin(30)")); // 0.5
        System.out.println("cos(60) = " + calculator.evaluateExpression("cos(60)")); // 0.5
        System.out.println("tan(45) = " + calculator.evaluateExpression("tan(45)")); // 1.0
        System.out.println("ctan(45) = " + calculator.evaluateExpression("ctan(45)")); // 1.0
        System.out.println("sin(pi/2) = " + calculator.evaluateExpression("sin(pi/2)")); // ≈1.0

        // 4. Комплексные выражения
        System.out.println("\nКомплексные выражения:");
        System.out.println("sin(x) + cos(y) = " + calculator.evaluateExpression("sin(x) + cos(y)"));
        System.out.println("sqrt(x^2 + y^2) = " + calculator.evaluateExpression("sqrt(x^2 + y^2)")); // √29 ≈ 5.385
        System.out.println("tan(x) * ctan(y) = " + calculator.evaluateExpression("tan(x) * ctan(y)"));

        // 5. Пример сложного выражения
        System.out.println("\nПример сложного выражения:");
        System.out.println(calculator.giveExample());

    }
}