package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

class MainTest {

    @BeforeEach
    void resetDepartmentCache() {
        Department.departmentCache.clear();
        Department.nextId = 1;
    }

    // Тесты для Department
    @Test
    void departmentShouldCacheInstances() {
        Department dept1 = Department.getDepartment("IT");
        Department dept2 = Department.getDepartment("HR");
        Department dept3 = Department.getDepartment("IT");

        assertEquals(1, dept1.getId());
        assertEquals(2, dept2.getId());
        assertSame(dept1, dept3);
    }

    @Test
    void departmentShouldReturnCorrectValuesFromGetters() {
        Department dept = new Department(5, "Finance");
        assertEquals(5, dept.getId());
        assertEquals("Finance", dept.getName());
    }

    // Тесты для Person
    @Test
    void personShouldStoreAllProvidedValues() {
        Department dept = new Department(1, "IT");
        Person person = new Person(101, "John Doe", "M", dept, 50000.0, "1990-01-01");

        assertEquals(101, person.getId());
        assertEquals("John Doe", person.getName());
        assertEquals("M", person.getGender());
        assertSame(dept, person.getDepartment());
        assertEquals(50000.0, person.getSalary(), 0.001);
        assertEquals("1990-01-01", person.getBirthDate());
    }

    // Тесты для PersonReader
    @Test
    void personReaderShouldParseCsvFile() {
        PersonReader reader = new PersonReader();
        ArrayList<Person> persons = reader.readFromCSV("foreign_names.csv", ';');

        assertFalse(persons.isEmpty());
        Person first = persons.get(0);
        assertNotNull(first.getName());
        assertNotNull(first.getDepartment());
    }

    @Test
    void personReaderShouldThrowExceptionForMissingFile() {
        PersonReader reader = new PersonReader();
        assertThrows(RuntimeException.class, () -> {
            reader.readFromCSV("missing_file.csv", ';');
        });
    }
}