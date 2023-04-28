package com.springboot.cmp.service;

import java.util.List;

import com.springboot.cmp.entity.Teacher;

public interface TeacherService {

    Teacher createTeacher(Teacher teacher);

    List<Teacher> getTeachers();

    Teacher getTeacherById(Long teacherId);
    
    void deleteTeacher(Long teacherId);
}
