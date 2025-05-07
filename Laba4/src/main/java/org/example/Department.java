package org.example;

import java.util.HashMap;

public class Department {
    private static int nextId = 1;
    private final int id;
    private final String name;

    private static HashMap<String, Department> departmentCache = new HashMap<>();
    public Department(int id, String name) {
        this.id = id;
        this.name = name;
    }

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

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
