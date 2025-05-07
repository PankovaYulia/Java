package org.example;

import java.util.HashMap;

/**
 * Класс, представляющий отдел в организации.
 * Реализует паттерн кэширования для повторного использования одинаковых отделов.
 */
public class Department {
    private static int nextId = 1;
    private final int id;
    private final String name;

    private static HashMap<String, Department> departmentCache = new HashMap<>();

    /**
     * Создает новый экземпляр отдела.
     *
     * @param id   уникальный идентификатор отдела
     * @param name название отдела
     */
    public Department(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Возвращает экземпляр отдела по имени, создавая новый при необходимости.
     * Реализует кэширование отделов для избежания дублирования.
     *
     * @param name название отдела
     * @return экземпляр класса Department
     */
    public static Department getDepartment(String name) {
        if (departmentCache.containsKey(name)) {
            return departmentCache.get(name);
        }
        else {
            Department department = new Department(nextId++, name);
            departmentCache.put(name, department);
            return department;
        }
    }

    /**
     * Возвращает идентификатор отдела
     */
    public int getId() {
        return id;
    }

    /**
     * Возвращает название отдела
     */
    public String getName() {
        return name;
    }
}