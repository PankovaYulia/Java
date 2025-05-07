package org.example;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        PersonReader reader = new PersonReader();

        ArrayList<Person> personList = reader.readFromCSV("foreign_names.csv", ';');
        for (Person person : personList) {
            System.out.println("ID: " + person.getId() + "; name: " + person.getName() + "; gender: " + person.getGender() +
                    "; department: " + person.getDepartment().getId() + "." + person.getDepartment().getName() + "; salary: " +
                    person.getSalary() + "; birth date: " + person.getBirthDate());
        }
    }
}