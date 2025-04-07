package org.example;

import org.example.InputReader;
import org.example.ExpressionCalculator;

public class Main {
    public static void main(String[] args) {
        InputReader inputReader = new InputReader();
        ExpressionCalculator calculator = new ExpressionCalculator();

        while (true) {
            System.out.println("Меню:");
            System.out.println("1. Добавить переменную и её значение");
            System.out.println("2. Вычислить значение выражения");
            System.out.println("3. Выйти");

            System.out.print("Выберите действие: ");
            int choice = inputReader.readIntFromConsole();

            switch (choice) {
                case 1:
                    System.out.print("Введите имя переменной: ");
                    String variableName = inputReader.readStringFromConsole();
                    System.out.print("Введите значение переменной: ");
                    double value = inputReader.readDoubleFromConsole();
                    calculator.addVariable(variableName, value);
                    System.out.println("Переменная добавлена.");
                    break;
                case 2:
                    System.out.println("Введите выражение для вычисления: ");
                    System.out.println("Пример: "+calculator.giveExample());
                    String expression = inputReader.readStringFromConsole();
                    double result = calculator.evaluateExpression(expression);
                    System.out.println("Результат выражения '" + expression + "': " + result);
                    break;
                case 3:
                    System.out.println("Программа завершена.");
                    return;
                default:
                    System.out.println("Неверный выбор. Пожалуйста, выберите существующий пункт меню.");
            }
        }
    }
}