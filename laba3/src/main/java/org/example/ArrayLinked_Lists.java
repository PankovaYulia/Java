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
    private void printResult(String operation, int NUM_OPERATIONS) {
        System.out.println("Operation\tCount\t");
        System.out.println(operation + "\t" + NUM_OPERATIONS );
        System.out.println(" ");
    }
    private void testAdd(int NUM_OPERATIONS) {
            for (int i = 0; i < NUM_OPERATIONS; i++) {
                arrayList.add(i);
            }

            for (int i = 0; i < NUM_OPERATIONS; i++) {
                linkedList.add(i);
            }

        printResult("Add     ", NUM_OPERATIONS);
    }
}