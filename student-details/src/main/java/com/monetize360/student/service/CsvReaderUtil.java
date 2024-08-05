package com.monetize360.student.service;

import com.monetize360.student.domain.Student;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class CsvReaderUtil {

    private CsvReaderUtil() {

    }

    public static List<Student> readDataFromFile(File file) {
        List<Student> students = new ArrayList<>();

        // Read the file
        try (FileReader fileReader = new FileReader(file); CSVReader reader = new CSVReader(fileReader)) {

            String[] nextLine;
            boolean isFirstLine = true;
            while ((nextLine = reader.readNext()) != null) {
                if (isFirstLine) {
                    isFirstLine = false; // Skip header line
                    continue;
                }
                String name = nextLine[0];
                String batch = nextLine[1];
                String completed = nextLine[2];
                String placed = nextLine[3];
                String qualification = nextLine[4];
                double score = Double.parseDouble(nextLine[5]);

                Student student = new Student(name, batch, completed, placed, qualification, score);
                students.add(student);
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        return students;
    }
}