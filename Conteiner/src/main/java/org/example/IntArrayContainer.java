package org.example;

public class IntArrayContainer {
    private int[] data;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public IntArrayContainer() {
        this(DEFAULT_CAPACITY);
    }

    public IntArrayContainer(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Initial capacity must be positive");
        }
        this.data = new int[initialCapacity];
        this.size = 0;
    }

    public void add(int value) {
        ensureCapacity();
        data[size++] = value;
    }

    public int get(int index) {
        checkIndex(index);
        return data[index];
    }

    public void remove(int index) {
        checkIndex(index);
        System.arraycopy(data, index + 1, data, index, size - index - 1);
        size--;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacity() {
        if (size == data.length) {
            int newCapacity = data.length * 2;
            int[] newData = new int[newCapacity];
            System.arraycopy(data, 0, newData, 0, size);
            data = newData;
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(data[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}