package org.example;

public class Main {
    public static void main(String[] args) {
        IntArrayContainer container = new IntArrayContainer(5);


        System.out.println("Добавляем элементы 10, 20, 30:");
        container.add(10);
        container.add(20);
        container.add(30);
        System.out.println(container);


        System.out.println("\nЭлемент с индексом 1: " + container.get(1));

        System.out.println("\nУдаляем элемент с индексом 0:");
        container.remove(0);
        System.out.println(container);


        System.out.println("\nТекущий размер контейнера: " + container.size());

        System.out.println("\nКонтейнер пустой? " + container.isEmpty());

        System.out.println("\nДобавляем еще 5 элементов:");
        for (int i = 1; i <= 5; i++) {
            container.add(i * 10);
        }
        System.out.println(container);

        try {
            System.out.println("\nПробуем получить элемент с индексом 10:");
            System.out.println(container.get(10));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}