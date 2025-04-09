package org.example;

import java.util.ArrayList;
import java.util.LinkedList;

public class ArrayLinked_Lists {
    private ArrayList<Integer> arrayList = new ArrayList<>();
    private LinkedList<Integer> linkedList = new LinkedList<>();
    private final int MAX_OPERATIONS = 16000;

    public final void testPerformance() {
        for (int i=1000; i<=MAX_OPERATIONS; i*=2){
            testAdd(i);
        }
    }
    private void printResult(String operation, long arrayListTime, long linkedListTime, int NUM_OPERATIONS) {
        System.out.println("Operation\tCount\tArrayList Time (ns)\tLinkedList Time (ns)");
        System.out.println(operation + "\t" + NUM_OPERATIONS + "\t\t\t" + arrayListTime + "\t\t\t" + linkedListTime);
        System.out.println(" ");
    }
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
    private long measureTime(Runnable task, int NUM_OPERATIONS) {
        long startTime = System.nanoTime();
        task.run();
        long endTime = System.nanoTime();
        return (endTime - startTime) / NUM_OPERATIONS;
    }
}