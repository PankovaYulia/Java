package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Тестовый класс для проверки функциональности класса IntArrayContainer.
 * Содержит тесты для основных операций: добавления, удаления, получения элементов,
 * проверки граничных условий и автоматического расширения емкости.
 */
public class IntArrayContainerTest {

    /**
     * Тестирует операции добавления и получения элементов.
     * Проверяет корректность работы методов add() и get(),
     * а также правильность подсчета размера контейнера.
     */
    @Test
    public void testAddAndGet() {
        IntArrayContainer container = new IntArrayContainer();
        container.add(10);
        container.add(20);

        assertEquals(2, container.size());
        assertEquals(10, container.get(0));
        assertEquals(20, container.get(1));
    }

    /**
     * Тестирует операцию удаления элемента.
     * Проверяет корректность работы метода remove(),
     * правильность изменения размера контейнера
     * и сдвига оставшихся элементов.
     */
    @Test
    public void testRemove() {
        IntArrayContainer container = new IntArrayContainer();
        container.add(10);
        container.add(20);
        container.add(30);

        container.remove(1);

        assertEquals(2, container.size());
        assertEquals(10, container.get(0));
        assertEquals(30, container.get(1));
    }

    /**
     * Тестирует обработку выхода за границы контейнера.
     * Проверяет, что при попытке получить или удалить элемент
     * по несуществующему индексу выбрасывается исключение IndexOutOfBoundsException.
     */
    @Test
    public void testIndexOutOfBounds() {
        IntArrayContainer container = new IntArrayContainer();
        container.add(10);

        assertThrows(IndexOutOfBoundsException.class, () -> container.get(1));
        assertThrows(IndexOutOfBoundsException.class, () -> container.remove(1));
    }

    /**
     * Тестирует поведение пустого контейнера.
     * Проверяет, что новый контейнер пуст (isEmpty() возвращает true)
     * и его размер равен 0.
     */
    @Test
    public void testEmptyContainer() {
        IntArrayContainer container = new IntArrayContainer();

        assertTrue(container.isEmpty());
        assertEquals(0, container.size());
    }

    /**
     * Тестирует автоматическое расширение емкости контейнера.
     * Проверяет, что при добавлении элементов сверх начальной емкости
     * контейнер корректно увеличивает свой размер.
     */
    @Test
    public void testCapacityExpansion() {
        IntArrayContainer container = new IntArrayContainer(2);
        container.add(1);
        container.add(2);
        container.add(3); // Должно вызвать расширение массива

        assertEquals(3, container.size());
        assertEquals(3, container.get(2));
    }
}