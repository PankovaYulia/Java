package org.example;

import org.example.InputReader;
import org.example.ExpressionCalculator;

public class Main {
    public static void main(String[] args) {
        InputReader inputReader = new InputReader();
        ExpressionCalculator calculator = new ExpressionCalculator();

        System.out.println("Введите переменные в формате 'имя = значение'");
        System.out.println("Для завершения ввода переменных введите 'end'");

        // Ввод переменных
        while (true) {
            System.out.print("> ");
            String input = inputReader.readStringFromConsole().trim();

            if (input.equalsIgnoreCase("end")) {
                break;
            }

            try {
                String[] parts = input.split("=");
                if (parts.length != 2) {
                    System.out.println("Неверный формат. Используйте: имя = значение");
                    continue;
                }

                String variableName = parts[0].trim();
                double value = Double.parseDouble(parts[1].trim());
                calculator.addVariable(variableName, value);
                System.out.println("Добавлена переменная: " + variableName + " = " + value);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: неверный формат числа");
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }

        // Ввод выражения для вычисления
        System.out.println("\nВведите выражение для вычисления:");
        System.out.println("Пример: " + calculator.giveExample());
        System.out.print("> ");
        String expression = inputReader.readStringFromConsole();

        try {
            double result = calculator.evaluateExpression(expression);
            System.out.println("Результат выражения '" + expression + "': " + result);
        } catch (Exception e) {
            System.out.println("Ошибка вычисления: " + e.getMessage());
        }
    }
}