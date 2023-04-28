package com.springboot.cmp.service.impl;

import com.springboot.cmp.entity.Course;
import com.springboot.cmp.entity.Teacher;
import com.springboot.cmp.exceptions.ResourceAlreadyExistsException;
import com.springboot.cmp.exceptions.ResourceNotFoundException;
import com.springboot.cmp.repository.CourseRepository;
import com.springboot.cmp.repository.TeacherRepository;
import com.springboot.cmp.service.CourseService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {

	private final CourseRepository courseRepository;
	private final TeacherRepository teacherRepository;

	@Autowired
	public CourseServiceImpl(CourseRepository courseRepository, TeacherRepository teacherRepository) {
		this.courseRepository = courseRepository;
		this.teacherRepository = teacherRepository;
	}

	// create a course
	@Override
	public Course createCourse(Course course) {
		Optional<Course> possibleExistingCourse = courseRepository.findByName(course.getName());
		if (possibleExistingCourse.isPresent())
			throw new ResourceAlreadyExistsException("Course", course.getName());
		return courseRepository.save(course);
	}

	// update a course
	@Override
	public Course updateCourse(Course course) {
		Course possibleExistingCourse = courseRepository
				.findById(course.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Course", "id", String.valueOf(course.getId())));

		Teacher possibleTeacher;

		if (course.getTeacher() != null) {
			possibleTeacher = teacherRepository
					.findById(course.getTeacher().getId())
					.orElseThrow(() -> new ResourceNotFoundException("Teacher", "id",
							String.valueOf(course.getTeacher().getId())));
		}

		possibleExistingCourse.setName(course.getName());

		possibleExistingCourse.setTeacher(course.getTeacher());

		Course updatedCourse = courseRepository.save(possibleExistingCourse);
		return updatedCourse;
	}

	// delete a course
	@Override
	public void deleteCourse(Long courseId) {
		courseRepository.findById(courseId)
				.orElseThrow(() -> new ResourceNotFoundException("Course", "id", String.valueOf(courseId)));
		courseRepository.deleteById(courseId);
	}

	// retrieve all courses
	@Override
	public List<Course> getAllCourses() {
		List<Course> possibleCourses = courseRepository.findAll();
		return possibleCourses;
	}

	// remove a teacher from a course
	@Override
	public void detachTeacher(Long courseId) {
		Course possibleExistingCourse = courseRepository.findById(courseId)
				.orElseThrow(() -> new ResourceNotFoundException("Course", "id", String.valueOf(courseId)));

		possibleExistingCourse.setId(courseId);
		possibleExistingCourse.setTeacher(null);
		courseRepository.save(possibleExistingCourse);
	}

	// retrieve a single course
	@Override
	public Course getCourseById(Long courseId) {
		Course possibleCourse = courseRepository.findById(courseId)
				.orElseThrow(() -> new ResourceNotFoundException("Course", "id", String.valueOf(courseId)));
		return possibleCourse;
	}
}
