package com.springboot.cmp.service;

import com.springboot.cmp.entity.Student;

import java.util.List;

public interface StudentService {

    Student createStudent(Student student);

    List<Student> getAllStudents();

    Student getStudentById(Long studentId);

    Student updateStudent(Student student);

    void deleteStudent(Long studentId);
}
