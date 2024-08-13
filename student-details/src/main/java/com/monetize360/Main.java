package com.monetize360;
import com.monetize360.student.dto.CountDTO;
import com.monetize360.student.service.CourseStatImpl;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
        CourseStatImpl courseStat=new CourseStatImpl();
        String qualification;
        //Question 1
        System.out.println("Question 1\nEnter a Qualification (e.g., BE, MCA, BSC):");
        qualification=sc.nextLine();
        courseStat.studentsByQualification(qualification).forEach(System.out::println);
        //2
        System.out.println("Question 2\nEnter a Qualification:");
        qualification=sc.nextLine();
        System.out.println("Total students for "+qualification+" is:"+courseStat.getStudentCountByQualification(qualification));
        //3
        System.out.println("Question 3\nTotal No of placed students are:"+courseStat.getPlacedStudentCount());
        //4
        System.out.println("Question 4\nTotal No of non placed students but completed the course are:"+courseStat.getNotPlacedStudentCount());
        //5
        CountDTO dto=courseStat.getPlacedAndNotPlacedCount();
        System.out.println("Question 5 \n"+dto);
        //6
        System.out.println("Question 6\nEnter a student's name:");
        String name=sc.nextLine();
        System.out.println(name+"'s details:"+courseStat.search(name));
        //7
        System.out.println("Question 7\nEnter a batch:");
        String batch=sc.nextLine();
        System.out.println("The success rate of this batch is:"+courseStat.successRate(batch));
        //8
        System.out.println("Question 8\nThe toppers are:");
        courseStat.maxScoreStudent().forEach(System.out::println);
        //9
        System.out.println("Question 9\nThe names of all students are given below:");
        courseStat.getStudentNames().forEach(stName -> System.out.print(stName + " "));
        System.out.println();
        //10
        System.out.println("Question 10\nThe details of all the student's are:");
        courseStat.getStudentDetails().forEach(System.out::println);
    }
}