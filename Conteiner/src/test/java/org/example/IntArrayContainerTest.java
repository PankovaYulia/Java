package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IntArrayContainerTest {
    @Test
    public void testAddAndGet() {
        IntArrayContainer container = new IntArrayContainer();
        container.add(10);
        container.add(20);

        assertEquals(2, container.size());
        assertEquals(10, container.get(0));
        assertEquals(20, container.get(1));
    }

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

    @Test
    public void testIndexOutOfBounds() {
        IntArrayContainer container = new IntArrayContainer();
        container.add(10);

        assertThrows(IndexOutOfBoundsException.class, () -> container.get(1));
        assertThrows(IndexOutOfBoundsException.class, () -> container.remove(1));
    }

    @Test
    public void testEmptyContainer() {
        IntArrayContainer container = new IntArrayContainer();

        assertTrue(container.isEmpty());
        assertEquals(0, container.size());
    }

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