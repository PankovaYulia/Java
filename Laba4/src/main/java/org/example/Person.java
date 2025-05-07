package org.example;

/**
 * Класс, представляющий сотрудника организации.
 * Содержит персональные данные и информацию о должности.
 */
public class Person {
    private int id;
    private String name;
    private String gender;
    private Department department;
    private double salary;
    private String birthDate;

    /**
     * Создает новый экземпляр сотрудника.
     *
     * @param id         уникальный идентификатор сотрудника
     * @param name       имя сотрудника
     * @param gender     пол сотрудника
     * @param department отдел, в котором работает сотрудник
     * @param salary     зарплата сотрудника
     * @param birthDate  дата рождения в формате строки
     */
    public Person(int id, String name, String gender, Department department, double salary, String birthDate) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.department = department;
        this.salary = salary;
        this.birthDate = birthDate;
    }

    /**
     * Возвращает идентификатор сотрудника.
     */
    public int getId() {
        return id;
    }

    /**
     * Возвращает имя сотрудника.
     */
    public String getName() {
        return name;
    }

    /**
     * Возвращает пол сотрудника.
     */
    public String getGender() {
        return gender;
    }

    /**
     * Возвращает отдел сотрудника.
     */
    public Department getDepartment() {
        return department;
    }

    /**
     * Возвращает зарплату сотрудника
     */
    public double getSalary() {
        return salary;
    }

    /**
     * Возвращает дату рождения сотрудника
     */
    public String getBirthDate() {
        return birthDate;
    }
}