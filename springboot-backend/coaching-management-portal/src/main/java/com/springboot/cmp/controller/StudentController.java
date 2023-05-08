package com.springboot.cmp.controller;

import com.springboot.cmp.entity.Student;
import com.springboot.cmp.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/students/")
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // REST API: create a student
    @PostMapping("create")
    public ResponseEntity<Student> createStudent(@Valid @RequestBody Student student) {
        Student newStudent = studentService.createStudent(student);
        return new ResponseEntity<>(newStudent, HttpStatus.CREATED);
    }

    // REST API: get all students
    @GetMapping("all")
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> allPossibleTeachers = studentService.getAllStudents();
        return new ResponseEntity<>(allPossibleTeachers, HttpStatus.OK);
    }

    // REST API: get a single student by id
    @GetMapping("get/{studentId}")
    public ResponseEntity<Student> getTeacherById(@PathVariable("studentId") Long studentId) {
        Student possibleStudent = studentService.getStudentById(studentId);
        return new ResponseEntity<Student>(possibleStudent, HttpStatus.OK);
    }

    // REST API: update a student
    @PutMapping("update/{studentId}")
    public ResponseEntity<Student> updateStudent(
            @PathVariable("studentId") Long studentId,
            @RequestBody(required = false) Student student
            ) {
        student.setId(studentId);

        Student updatedStudent = studentService.updateStudent(student);
        return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
    }

    // REST API: delete a student
}
