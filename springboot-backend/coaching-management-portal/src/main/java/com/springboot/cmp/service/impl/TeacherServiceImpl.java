package com.springboot.cmp.service.impl;

import com.springboot.cmp.entity.Teacher;
import com.springboot.cmp.exceptions.EmailAlreadyExistsException;
import com.springboot.cmp.exceptions.InvalidEmailException;
import com.springboot.cmp.exceptions.ResourceNotFoundException;
import com.springboot.cmp.repository.TeacherRepository;
import com.springboot.cmp.service.TeacherService;
import com.springboot.cmp.util.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    // create teacher
    @Override
    public Teacher createTeacher(Teacher teacher) {
        Optional<Teacher> possibleExistingTeacher = teacherRepository.findByEmailId(teacher.getEmailId());
        if (possibleExistingTeacher.isPresent())
            throw new EmailAlreadyExistsException(
                    String.format("the email `%s` already exists in the database", teacher.getEmailId()));

        if (!EmailValidator.isEmailValid(teacher.getEmailId()))
            throw new InvalidEmailException(teacher.getEmailId());
        return teacherRepository.save(teacher);
    }

    // fetch a teacher by their id
    @Override
    public Teacher getTeacherById(Long teacherId) {
        return teacherRepository.findById(teacherId)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher", "id", String.valueOf(teacherId)));
    }

    @Override
    public void deleteTeacher(Long teacherId) {
        teacherRepository.findById(teacherId)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher", "id", String.valueOf(teacherId)));

        Optional<Teacher> possibleExistingTeacher = teacherRepository.findById(teacherId);
        if (possibleExistingTeacher.isPresent()) {
            possibleExistingTeacher.get().setCourses(null);
            teacherRepository.deleteById(possibleExistingTeacher.get().getId());
        }

    }

    @Override
    public List<Teacher> getTeachers() {
        return teacherRepository.findAll();
    }

}
