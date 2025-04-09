package org.example;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Класс для тестирования производительности ArrayList и LinkedList.
 * Сравнивает время выполнения основных операций для двух типов списков.
 */
public class ArrayLinked_Lists {
    private ArrayList<Integer> arrayList = new ArrayList<>();
    private LinkedList<Integer> linkedList = new LinkedList<>();
    private final int MAX_OPERATIONS = 16000;

    /**
     * Запускает тестирование производительности для операций:
     * добавления, получения, проверки наличия, удаления и очистки элементов.
     * Тестирование выполняется для разного количества операций (1000, 2000, 4000, ..., 16000).
     */
    public final void testPerformance() {
        for (int i=1000; i<=MAX_OPERATIONS; i*=2){
            testAdd(i);
            testGet(i);
            testContains(i);
            testDelete(i);
            testClear(i);
        }
    }

    /**
     * Выводит результаты тестирования в консоль.
     * @param operation Название тестируемой операции
     * @param arrayListTime Время выполнения операции для ArrayList (в наносекундах на операцию)
     * @param linkedListTime Время выполнения операции для LinkedList (в наносекундах на операцию)
     * @param NUM_OPERATIONS Количество операций
     */
    private void printResult(String operation, long arrayListTime, long linkedListTime, int NUM_OPERATIONS) {
        System.out.println("Operation\tCount\tArrayList Time (ns)\tLinkedList Time (ns)");
        System.out.println(operation + "\t" + NUM_OPERATIONS + "\t\t\t" + arrayListTime + "\t\t\t" + linkedListTime);
        System.out.println(" ");
    }

    /**
     * Тестирует производительность операции добавления элементов.
     * @param NUM_OPERATIONS Количество операций добавления
     */
    private void testAdd(int NUM_OPERATIONS) {
        long arrayListAddTime = measureTime(() -> {
            for (int i = 0; i < NUM_OPERATIONS; i++) {
                arrayList.add(i);
            }
        }, NUM_OPERATIONS);

        long linkedListAddTime = measureTime(() -> {
            for (int i = 0; i < NUM_OPERATIONS; i++) {
                linkedList.add(i);
            }
        }, NUM_OPERATIONS);

        printResult("Add     ", arrayListAddTime, linkedListAddTime, NUM_OPERATIONS);
    }

    /**
     * Тестирует производительность операции получения элементов по индексу.
     * @param NUM_OPERATIONS Количество операций получения
     */
    private void testGet(int NUM_OPERATIONS) {
        long arrayListGetTime = measureTime(() -> {
            for (int i = 0; i < NUM_OPERATIONS; i++) {
                arrayList.get(i);
            }
        }, NUM_OPERATIONS);

        long linkedListGetTime = measureTime(() -> {
            for (int i = 0; i < NUM_OPERATIONS; i++) {
                linkedList.get(i);
            }
        }, NUM_OPERATIONS);

        printResult("Get     ", arrayListGetTime, linkedListGetTime, NUM_OPERATIONS);
    }

    /**
     * Тестирует производительность операции проверки наличия элементов.
     * @param NUM_OPERATIONS Количество операций проверки
     */
    private void testContains(int NUM_OPERATIONS) {
        long arrayListContainsTime = measureTime(() -> {
            for (int i = 0; i < NUM_OPERATIONS; i++) {
                arrayList.contains(i);
            }
        }, NUM_OPERATIONS);

        long linkedListContainsTime = measureTime(() -> {
            for (int i = 0; i < NUM_OPERATIONS; i++) {
                linkedList.contains(i);
            }
        }, NUM_OPERATIONS);

        printResult("Contains", arrayListContainsTime, linkedListContainsTime, NUM_OPERATIONS);
    }

    /**
     * Тестирует производительность операции удаления элементов из начала списка.
     * @param NUM_OPERATIONS Количество операций удаления
     */
    private void testDelete(int NUM_OPERATIONS) {
        long arrayListDeleteTime = measureTime(() -> {
            for (int i = 0; i < NUM_OPERATIONS; i++) {
                arrayList.remove(0);
            }
        }, NUM_OPERATIONS);

        long linkedListDeleteTime = measureTime(() -> {
            for (int i = 0; i < NUM_OPERATIONS; i++) {
                linkedList.removeFirst();
            }
        }, NUM_OPERATIONS);

        printResult("Delete  ", arrayListDeleteTime, linkedListDeleteTime, NUM_OPERATIONS);
    }

    /**
     * Тестирует производительность операции очистки списка.
     * Перед тестированием заполняет списки указанным количеством элементов.
     * @param NUM_OPERATIONS Количество элементов для добавления перед очисткой
     */
    private void testClear(int NUM_OPERATIONS) {
        for (int i = 0; i < NUM_OPERATIONS; i++) {
            arrayList.add(i);
            linkedList.add(i);
        }

        long arrayListClearTime = measureTime(() -> {
            arrayList.clear();
        }, NUM_OPERATIONS);

        long linkedListClearTime = measureTime(() -> {
            linkedList.clear();
        }, NUM_OPERATIONS);

        printResult("Clear   ", arrayListClearTime, linkedListClearTime, NUM_OPERATIONS);
    }

    /**
     * Измеряет среднее время выполнения одной операции.
     * @param task Задача, время выполнения которой нужно измерить
     * @param NUM_OPERATIONS Количество операций в задаче
     * @return Среднее время выполнения одной операции в наносекундах
     */
    private long measureTime(Runnable task, int NUM_OPERATIONS) {
        long startTime = System.nanoTime();
        task.run();
        long endTime = System.nanoTime();
        return (endTime - startTime) / NUM_OPERATIONS;
    }
}