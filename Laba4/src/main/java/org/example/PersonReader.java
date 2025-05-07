package org.example;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVParserBuilder;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class PersonReader {
    public ArrayList<Person> readFromCSV(String filepath, char separator) {
        ArrayList<Person> personList = new ArrayList<>();

        try (InputStream in = getClass().getClassLoader().getResourceAsStream(filepath)) {
            try (CSVReader reader = in == null ? null : new CSVReaderBuilder(new InputStreamReader(in))
                    .withCSVParser(new CSVParserBuilder().withSeparator(separator).build())
                    .build()) {
                String[] nextLine;
                reader.readNext();
                while ((nextLine = reader.readNext()) != null) {
                    int id = Integer.parseInt(nextLine[0]);
                    String name = nextLine[1];
                    String gender = nextLine[2];
                    String birthDate = nextLine[3];
                    Department department = Department.getDepartment(nextLine[4]);
                    double salary = Double.parseDouble(nextLine[5]);
                    Person person = new Person(id, name, gender, department, salary, birthDate);
                    personList.add(person);
                }
            }
        }
        catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
        }
        return personList;
    }
}
