package com.springboot.cmp.controller;

import com.springboot.cmp.entity.Teacher;
import com.springboot.cmp.service.TeacherService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/teachers")
public class TeacherController {

    private TeacherService teacherService;
    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    // REST API: create a teacher
//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("create")
    public ResponseEntity<Teacher> createTeacher(@Valid @RequestBody Teacher teacher) {
        Teacher newTeacher = teacherService.createTeacher(teacher);
        return new ResponseEntity<>(newTeacher, HttpStatus.CREATED);
    }

    // REST API: get all teachers
    @GetMapping("all")
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        List<Teacher> teachers = teacherService.getTeachers();
        return new ResponseEntity<>(teachers, HttpStatus.OK);
    }

    // REST API: get a single teacher by id
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("get/{teacherId}")
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
