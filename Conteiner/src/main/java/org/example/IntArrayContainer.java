package org.example;

/**
 * Контейнер для хранения и управления массивом целых чисел.
 * Предоставляет базовые операции: добавление, удаление, получение элементов и управление размером.
 */
public class IntArrayContainer {
    private int[] array;
    private int size;

    /**
     * Создает пустой контейнер с начальной емкостью по умолчанию (10 элементов).
     */
    public IntArrayContainer() {
        this(10);
    }

    /**
     * Создает пустой контейнер с указанной начальной емкостью.
     *
     * @param initialCapacity начальная емкость контейнера
     * @throws IllegalArgumentException если указана отрицательная начальная емкость
     */
    public IntArrayContainer(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Недопустимая емкость: " + initialCapacity);
        }
        this.array = new int[initialCapacity];
        this.size = 0;
    }

    /**
     * Добавляет указанный элемент в конец контейнера.
     * При необходимости автоматически увеличивает емкость контейнера.
     *
     * @param element элемент для добавления
     */
    public void add(int element) {
        if (size == array.length) {
            expandCapacity();
        }
        array[size++] = element;
    }

    /**
     * Возвращает элемент по указанному индексу.
     *
     * @param index индекс элемента
     * @return элемент по указанному индексу
     * @throws IndexOutOfBoundsException если индекс выходит за границы 
     */
    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс: " + index + ", Размер: " + size);
        }
        return array[index];
    }

    /**
     * Удаляет элемент по указанному индексу.
     * Сдвигает все последующие элементы влево.
     *
     * @param index индекс удаляемого элемента
     * @throws IndexOutOfBoundsException если индекс выходит за границы
     */
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс: " + index + ", Размер: " + size);
        }
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        size--;
    }

    /**
     * Возвращает текущее количество элементов в контейнере.
     *
     * @return количество элементов
     */
    public int size() {
        return size;
    }

    /**
     * Проверяет, пуст ли контейнер.
     *
     * @return true если контейнер не содержит элементов
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Увеличивает емкость контейнера при заполнении.
     */
    private void expandCapacity() {
        int newCapacity = (array.length * 3) / 2 + 1;
        int[] newArray = new int[newCapacity];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }
}