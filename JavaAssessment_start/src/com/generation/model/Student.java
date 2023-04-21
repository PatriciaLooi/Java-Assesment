package com.generation.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Student extends Person
{
    public static final double PASS_MIN_GRADE = 3.0;

    private final HashMap<String, EnrolledCourse> enrolledCourses = new HashMap<>();


    public Student( String id, String name, String email, Date birthDate )
    {
        super( id, name, email, birthDate );
    }

    public boolean enrollToCourse( Course course )
    {
        //TODO Check if student has already enrolled to the course, if not add the course to enrolledCourses hashmap
        if (!enrolledCourses.containsKey(course.getCode())) {
            enrolledCourses.put(course.getCode(), new EnrolledCourse(course));
            return true;
        }
        return false;
    }

    public HashMap<String, EnrolledCourse> getEnrolledCourses()
    {
        //TODO return a Hashmap of all the enrolledCourses
        return enrolledCourses;
    }

    public void gradeCourse( String courseCode, double grade )
    {
        //TODO set the grade for the enrolled Course
        //enrolledCourses.get(courseCode).setGrade(grade)
        if (enrolledCourses.containsKey(courseCode)) {
            enrolledCourses.get(courseCode).setGrade(grade);
        }
    }

    public Course findCourseById( String courseId )
    {
        //TODO return a Course from the course Id
        for (EnrolledCourse enrolledCourse : enrolledCourses.values()) {
            if (enrolledCourse.getCode().equals(courseId)) {
                return enrolledCourse;
            }
        }
        return null;
    }

    public HashMap<String, EnrolledCourse> findPassedCourses()
    {
        //TODO Check the enrolled courses grade and compare to the passing grade
        //Loop through the enrolledCourses, getGrade(), > 3.0

        HashMap<String, EnrolledCourse> passedCourses = new HashMap<>();

        for (EnrolledCourse enrolledCourse : enrolledCourses.values()) {
            if (enrolledCourse.getGrade() >= PASS_MIN_GRADE) {
                passedCourses.put(enrolledCourse.getCode(), enrolledCourse);
            }
        }

        return passedCourses;
    }


    public String toString()
    {
        return "Student {" + super.toString() + "}";
    }
}

