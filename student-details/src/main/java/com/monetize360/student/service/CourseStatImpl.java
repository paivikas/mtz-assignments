package com.monetize360.student.service;

import com.monetize360.Main;
import com.monetize360.student.domain.Student;
import com.monetize360.student.dto.CountDTO;
import com.monetize360.student.dto.StudentDTO;

import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


public class CourseStatImpl implements CourseStat{
    ClassLoader classLoader = CsvReaderUtil.class.getClassLoader();
    File file = new File(Objects.requireNonNull(classLoader.getResource("coursedata.csv")).getFile());
    List<Student> students = CsvReaderUtil.readDataFromFile(file);
    @Override
    public List<Student> studentsByQualification(String qualification) {
        return students.stream()
                .filter(student -> student.getQualification().equalsIgnoreCase(qualification))
                .collect(Collectors.toList());
    }

    @Override
    public int getStudentCountByQualification(String qualification) {

        return studentsByQualification(qualification).size();
    }

    @Override
    public int getPlacedStudentCount() {
        return (int) students.stream()
                .filter(student -> "Y".equalsIgnoreCase(student.getPlaced()))
                .count();
    }

    @Override
    public int getNotPlacedStudentCount() {
        return (int) students.stream()
                .filter(student -> "Y".equalsIgnoreCase(student.getCompleted()))
                .filter(student -> "N".equalsIgnoreCase(student.getPlaced()))
                .count();
    }

    @Override
    public CountDTO getPlacedAndNotPlacedCount() {
        CountDTO dto=new CountDTO();
        dto.setPlacedCount(getPlacedStudentCount());
        dto.setNotPlacedCount((int) students.stream().filter(student -> "N".equalsIgnoreCase(student.getPlaced()))
                .count());
        return dto;
    }

    @Override
    public List<Student> search(String str) {
        return students.stream()
                .filter(student -> student.getName().toLowerCase().contains(str.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public float successRate(String batchName) {
        long totalStudents = students.stream()
                .filter(student -> student.getBatch().equalsIgnoreCase(batchName))
                .count();

        long placedStudents = students.stream()
                .filter(student -> student.getBatch().equalsIgnoreCase(batchName))
                .filter(student -> "Y".equalsIgnoreCase(student.getCompleted()))
                .count();

        // Handle the case where there are no students in the specified batch
        if (totalStudents == 0) {
            return 0.0f;
        }

        return (float) placedStudents / totalStudents * 100;
    }

    @Override
    public List<Student> maxScoreStudent() {
        double maxScore = students.stream()
                .mapToDouble(Student::getScore)
                .max()
                .orElse(Double.NaN); // Handle the case when there are no students

        // Find all students with the highest score
        return students.stream()
                .filter(student -> student.getScore() == maxScore)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getStudentNames() {
        return students.stream()
                .map(Student::getName)
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentDTO> getStudentDetails() {
        return students.stream()
                .map(student -> new StudentDTO(
                        student.getName(),
                        student.getQualification(),
                        student.getScore()
                ))
                .collect(Collectors.toList());
    }
}
