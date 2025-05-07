package org.example;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVParserBuilder;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {
        ArrayList<Person> personList = readFromCSV("foreign_names.csv", ';');
        for (Person person : personList) {
            System.out.println("ID: " + person.id + "; name: " + person.name + "; gender: " + person.gender +
                    "; department: " + person.departmentId + "." + person.departmentName +
                    "; salary: " + person.salary + "; birth date: " + person.birthDate);
        }
    }

    private static ArrayList<Person> readFromCSV(String filepath, char separator) {
        ArrayList<Person> personList = new ArrayList<>();
        HashMap<String, Integer> departmentIds = new HashMap<>();
        AtomicInteger nextDeptId = new AtomicInteger(1); // Используем AtomicInteger вместо int

        try (InputStream in = Main.class.getClassLoader().getResourceAsStream(filepath)) {
            try (CSVReader reader = in == null ? null : new CSVReaderBuilder(new InputStreamReader(in))
                    .withCSVParser(new CSVParserBuilder().withSeparator(separator).build())
                    .build()) {
                String[] nextLine;
                reader.readNext(); // Пропускаем заголовок
                while ((nextLine = reader.readNext()) != null) {
                    int id = Integer.parseInt(nextLine[0]);
                    String name = nextLine[1];
                    String gender = nextLine[2];
                    String birthDate = nextLine[3];
                    String deptName = nextLine[4];

                    // Получаем или создаём ID для департамента
                    int deptId = departmentIds.computeIfAbsent(deptName,
                            k -> nextDeptId.getAndIncrement()); // Используем getAndIncrement

                    double salary = Double.parseDouble(nextLine[5]);
                    personList.add(new Person(id, name, gender, deptId, deptName, salary, birthDate));
                }
            }
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
        }
        return personList;
    }

    static class Person {
        int id;
        String name;
        String gender;
        int departmentId;
        String departmentName;
        double salary;
        String birthDate;

        public Person(int id, String name, String gender, int departmentId, String departmentName,
                      double salary, String birthDate) {
            this.id = id;
            this.name = name;
            this.gender = gender;
            this.departmentId = departmentId;
            this.departmentName = departmentName;
            this.salary = salary;
            this.birthDate = birthDate;
        }
    }
}