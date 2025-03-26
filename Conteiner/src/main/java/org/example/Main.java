package org.example;

import java.util.Scanner;

/**
 * Главный класс приложения для демонстрации работы контейнера IntArrayContainer.
 * Предоставляет консольный интерфейс для взаимодействия с пользователем.
 */
public class Main {

    /**
     * Точка входа в приложение.
     * Создает контейнер и запускает интерактивное меню.
     *
     */
    public static void main(String[] args) {
        IntArrayContainer container = new IntArrayContainer();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            System.out.print("Выберите действие: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        addElement(container, scanner);
                        break;
                    case 2:
                        getElement(container, scanner);
                        break;
                    case 3:
                        removeElement(container, scanner);
                        break;
                    case 4:
                        printContainer(container);
                        break;
                    case 5:
                        System.out.println("Размер контейнера: " + container.size());
                        break;
                    case 6:
                        System.out.println("Программа завершена.");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Некорректный выбор, попробуйте еще раз.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите число от 1 до 6");
            }
        }
    }

    /**
     * Выводит на экран меню доступных действий.
     */
    private static void printMenu() {
        System.out.println("\nМеню:");
        System.out.println("1. Добавить элемент");
        System.out.println("2. Получить элемент по индексу");
        System.out.println("3. Удалить элемент по индексу");
        System.out.println("4. Вывести все элементы");
        System.out.println("5. Показать размер контейнера");
        System.out.println("6. Выйти");
    }

    /**
     * Добавляет новый элемент в контейнер.
     * Запрашивает у пользователя значение элемента и обрабатывает возможные ошибки ввода.
     * После добавления выводит текущее содержимое контейнера.
     *
     * @param container контейнер для добавления элемента
     * @param scanner объект Scanner для ввода данных
     */
    private static void addElement(IntArrayContainer container, Scanner scanner) {
        System.out.print("Введите целое число для добавления: ");
        try {
            int value = Integer.parseInt(scanner.nextLine());
            container.add(value);
            System.out.println("Элемент " + value + " добавлен в контейнер.");
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: введите целое число");
        }
        System.out.println("Содержимое контейнера (" + container.size() + " элементов):");
        for (int i = 0; i < container.size(); i++) {
            System.out.println("[" + i + "]: " + container.get(i));
        }
    }

    /**
     * Получает и выводит элемент по указанному индексу.
     * Проверяет, не пуст ли контейнер, и обрабатывает возможные ошибки ввода.
     *
     * @param container контейнер для получения элемента
     * @param scanner объект Scanner для ввода данных
     */
    private static void getElement(IntArrayContainer container, Scanner scanner) {
        if (container.isEmpty()) {
            System.out.println("Контейнер пуст.");
            return;
        }

        System.out.print("Введите индекс элемента: ");
        try {
            int index = Integer.parseInt(scanner.nextLine());
            System.out.println("Элемент с индексом " + index + ": " + container.get(index));
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: введите целое число");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Ошибка: неверный индекс");
        }
    }

    /**
     * Удаляет элемент по указанному индексу.
     * Проверяет, не пуст ли контейнер, и обрабатывает возможные ошибки ввода.
     * После удаления выводит текущее содержимое контейнера.
     *
     * @param container контейнер для удаления элемента
     * @param scanner объект Scanner для ввода данных
     */
    private static void removeElement(IntArrayContainer container, Scanner scanner) {
        if (container.isEmpty()) {
            System.out.println("Контейнер пуст.");
            return;
        }

        System.out.print("Введите индекс элемента для удаления: ");
        try {
            int index = Integer.parseInt(scanner.nextLine());
            int removedValue = container.get(index);
            container.remove(index);
            System.out.println("Элемент " + removedValue + " удален из контейнера.");
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: введите целое число");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Ошибка: неверный индекс");
        }
        if (container.isEmpty()) {
            System.out.println("Контейнер пуст.");
            return;
        }

        System.out.println("Содержимое контейнера (" + container.size() + " элементов):");
        for (int i = 0; i < container.size(); i++) {
            System.out.println("[" + i + "]: " + container.get(i));
        }
    }

    /**
     * Выводит все элементы контейнера.
     * Проверяет, не пуст ли контейнер перед выводом.
     *
     * @param container контейнер для вывода элементов
     */
    private static void printContainer(IntArrayContainer container) {
        if (container.isEmpty()) {
            System.out.println("Контейнер пуст.");
            return;
        }

        System.out.println("Содержимое контейнера (" + container.size() + " элементов):");
        for (int i = 0; i < container.size(); i++) {
            System.out.println("[" + i + "]: " + container.get(i));
        }
    }
}