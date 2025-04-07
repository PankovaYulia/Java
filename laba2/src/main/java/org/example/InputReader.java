package org.example;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Utility class for reading input (int) from the console using Scanner.
 */
public class InputReader {
    private final Scanner scanner;

    /**
     * Constructor to initialize the InputReader with a new Scanner object.
     */
    public InputReader() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads an integer input from the console.
     *
     * @return the integer value read from the console
     */
    public int readIntFromConsole() {
        int choice = 0;
        boolean isValidInput = false;
        while (!isValidInput) {
            try {
                choice = scanner.nextInt();
                isValidInput = true; // Если удалось считать число, устанавливаем флаг в true
            } catch (InputMismatchException e) {
                System.out.println("Ошибка: Введите целое число.");
                scanner.nextLine(); // очистка буфера сканера
            }
        }
        return choice;
    }
    /**
     * Reads a double input from the console.
     *
     * @return the double value read from the console
     */
    public double readDoubleFromConsole() {
        double number = 0;
        boolean isValidInput = false;
        while (!isValidInput) {
            try {
                number = scanner.nextDouble();
                isValidInput = true; // Если удалось считать число, устанавливаем флаг в true
            } catch (InputMismatchException e) {
                System.out.println("Ошибка: Введите число.");
                scanner.nextLine(); // очистка буфера сканера
            }
        }
        return number;
    }

    /**
     * Reads a string input from the console.
     *
     * @return the string value read from the console
     */
    public String readStringFromConsole() {
        String input = "";
        boolean isValidInput = false;
        while (!isValidInput) {
            try {
                input = scanner.next();
                isValidInput = true; // Если удалось считать строку, устанавливаем флаг в true
            } catch (NoSuchElementException e) {
                System.out.println("Ошибка: Введите строку.");
                scanner.nextLine(); // очистка буфера сканера
            }
        }
        return input;
    }

    /**
     * Closes the underlying Scanner object.
     */
    public void closeScanner() {
        scanner.close();
    }
}