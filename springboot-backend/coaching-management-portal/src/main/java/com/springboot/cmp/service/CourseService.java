package com.springboot.cmp.service;

import java.util.List;

import com.springboot.cmp.entity.Course;

public interface CourseService {

    Course createCourse(Course course);
    
    List<Course> getAllCourses();

    Course getCourseById(Long courseId);

    Course updateCourse(Course course);
    
    void deleteCourse(Long courseId);

    void detachTeacher(Long courseId);
    
    
}
