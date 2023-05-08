package com.springboot.cmp.service.impl;

import com.springboot.cmp.entity.Student;
import com.springboot.cmp.exceptions.EmailAlreadyExistsException;
import com.springboot.cmp.exceptions.InvalidEmailException;
import com.springboot.cmp.exceptions.ResourceNotFoundException;
import com.springboot.cmp.repository.CourseRepository;
import com.springboot.cmp.repository.StudentRepository;
import com.springboot.cmp.repository.TeacherRepository;
import com.springboot.cmp.service.StudentService;
import com.springboot.cmp.util.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public StudentServiceImpl(
            StudentRepository studentRepository,
            TeacherRepository teacherRepository,
            CourseRepository courseRepository
    ) {

        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
    }

    // create student
    @Override
    public Student createStudent(Student student) {
        Optional<Student> possibleExistingStudent = studentRepository.findByEmailId(student.getEmailId());

        if (possibleExistingStudent.isPresent())
            throw new EmailAlreadyExistsException(
                    String.format("the email `%s` already exists in the database", student.getEmailId()));

        if (!EmailValidator.isEmailValid(student.getEmailId()))
            throw new InvalidEmailException(student.getEmailId());
        return studentRepository.save(student);
    }

    // fetch all the students
    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }


    // fetch a student by their id
    @Override
    public Student getStudentById(Long studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "id", String.valueOf(studentId)));
    }

    @Override
    public Student updateStudent(Student student) {
        Student possibleExistingStudent = studentRepository.findById(student.getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student", "id", String.valueOf(student.getId())));

        if (student.getAddress() != null)
            possibleExistingStudent.setAddress(student.getAddress());

        if (student.getEnrolledCourses() != null)
            possibleExistingStudent.setEnrolledCourses(student.getEnrolledCourses());

        return studentRepository.save(possibleExistingStudent);
    }

    @Override
    public void deleteStudent(Long studentId) {
        studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "id", String.valueOf(studentId)));

        Optional<Student> possibleExistingStudent = studentRepository.findById(studentId);

        if (possibleExistingStudent.isPresent()) {
            possibleExistingStudent.get().setAddress(null);
            possibleExistingStudent.get().setEnrolledCourses(null);
            studentRepository.deleteById(possibleExistingStudent.get().getId());
        }
    }
}
