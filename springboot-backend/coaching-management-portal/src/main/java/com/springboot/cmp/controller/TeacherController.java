package com.springboot.cmp.controller;

import com.springboot.cmp.entity.Teacher;
import com.springboot.cmp.service.TeacherService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/teachers")
public class TeacherController {

    private TeacherService teacherService;
    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    // REST API: create a teacher
    @PostMapping("create")
    public ResponseEntity<Teacher> createTeacher(@Valid @RequestBody Teacher teacher) {
        Teacher newTeacher = teacherService.createTeacher(teacher);
        return new ResponseEntity<>(newTeacher, HttpStatus.CREATED);
    }

    // REST API: get all teachers
    @GetMapping()
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        List<Teacher> teachers = teacherService.getTeachers();
        return new ResponseEntity<>(teachers, HttpStatus.OK);
    }

    // REST API: get a teacher
    @GetMapping("{teacherId}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable("teacherId") Long teacherId) {
        Teacher teacher = teacherService.getTeacherById(teacherId);
        return new ResponseEntity<Teacher>(teacher, HttpStatus.OK);
    }

    // REST API: update a teacher
    
    // REST API: delete a teacher
    @DeleteMapping("delete/{teacherId}")
    public ResponseEntity<String> deleteTeacher(@PathVariable("teacherId") Long teacherId) {
    	teacherService.deleteTeacher(teacherId);
    	return new ResponseEntity<String>(
    			String.format("Teacher with id %d is successfully deleted.", teacherId),
    			HttpStatus.OK);
    }
    
    
}
