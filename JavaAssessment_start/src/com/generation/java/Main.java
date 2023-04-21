package com.generation.java;

import com.generation.model.Course;
import com.generation.model.EnrolledCourse;
import com.generation.model.Student;
import com.generation.service.CourseService;
import com.generation.service.StudentService;
import com.generation.utils.PrinterHelper;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.List;

public class Main {

    public static void main(String[] args)
            throws ParseException {
        StudentService studentService = new StudentService();
        CourseService courseService = new CourseService();
        Scanner scanner = new Scanner(System.in);
        int option;
        do {
            PrinterHelper.showMainMenu();
            option = scanner.nextInt();
            switch (option) {
                case 1:
                    registerStudent(studentService, scanner);
                    break;
                case 2:
                    findStudent(studentService, scanner);
                    break;
                case 3:
                    gradeStudent(studentService, scanner);
                    break;
                case 4:
                    enrollStudentToCourse(studentService, courseService, scanner);
                    break;
                case 5:
                    showStudentsSummary(studentService, scanner);
                    break;
                case 6:
                    showCoursesSummary(courseService, scanner);
                    break;
                case 7:
                    showPassedCourses(studentService, scanner);
                    break;
            }
        }
        while (option != 8);
    }

    private static void enrollStudentToCourse(StudentService studentService, CourseService courseService,
                                              Scanner scanner) {
        System.out.println("Insert student ID");
        String studentId = scanner.next();
        Student student = studentService.findStudent(studentId);
        if (student == null) {
            System.out.println("Invalid Student ID");
            return;
        }
        System.out.println(student);
        System.out.println("Insert course ID");
        String courseId = scanner.next();
        Course course = courseService.getCourse(courseId);
        if (course == null) {
            System.out.println("Invalid Course ID");
            return;
        }
        System.out.println(course);
        studentService.enrollToCourse(studentId, course);
        System.out.println("Student with ID: " + studentId + " enrolled successfully to " + courseId);

    }

    private static void showCoursesSummary(CourseService courseService, Scanner scanner) {
        courseService.showSummary();
    }

    private static void showStudentsSummary(StudentService studentService, Scanner scanner) {
        studentService.showSummary();
    }

    private static void gradeStudent(StudentService studentService, Scanner scanner) {
        Student student = getStudentInformation(studentService, scanner);
        System.out.println("Enrolled course:");

        //TODO Loop through the student enrolled courses, and use the scanner object to get the course ID to insert
        // the course grade

        //1) Get the Student Object / e.g Student 111 - from studentService object
        //2) Get all the enrolledCourses for that student 111 - studentService object
        //HashMap<String, EnrolledCourse> getEnrolledCourse = studentService.getEnrolledCourses();
        // For Loop to loop through getEnrolledCourse and print out
        //3) allow user to enter the course code (find the enrolledCourse object - use findEnrolledCourse( student, courseId ))
        //4) allow user to enter the grade
        //5) studentService.gradeStudent(student, course, grade)


        if (student == null) {
            return;
        }
        System.out.println("Enrolled courses:");
        HashMap<String, EnrolledCourse> enrolledCourses = student.getEnrolledCourses();

        for (EnrolledCourse enrolledCourse : enrolledCourses.values()) {
            System.out.println(enrolledCourse.getCode() + ": " + enrolledCourse.getName());
            System.out.println("Insert grade for course " + enrolledCourse.getName());
            double grade = scanner.nextDouble();
            enrolledCourse.setGrade(grade);
            System.out.println("Grade " + grade + " saved for course " + enrolledCourse.getName());
        }
    }


    private static void findStudent(StudentService studentService, Scanner scanner) {
        Student student = getStudentInformation(studentService, scanner);
        if (student != null) {
            System.out.println("Student Found: ");
            System.out.println(student);
        }
    }


    private static Student getStudentInformation(StudentService studentService, Scanner scanner) {
        System.out.println("Enter student ID: ");
        String studentId = scanner.next();
        Student student = studentService.findStudent(studentId);
        if (student == null) {
            System.out.println("Student not found");
        }
        return student;
    }

    private static void registerStudent(StudentService studentService, Scanner scanner) throws ParseException {
        Student student = PrinterHelper.createStudentMenu(scanner);
        studentService.registerStudent(student);
    }


}

