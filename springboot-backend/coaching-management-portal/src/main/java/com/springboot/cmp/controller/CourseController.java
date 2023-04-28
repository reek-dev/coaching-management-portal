package com.springboot.cmp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.cmp.entity.Course;
import com.springboot.cmp.service.CourseService;
import com.springboot.cmp.service.TeacherService;

@RestController
@RequestMapping("api/courses")
public class CourseController {

	private CourseService courseService;
	private TeacherService teacherService;

	@Autowired
	public CourseController(CourseService courseService, TeacherService teacherService) {
		this.courseService = courseService;
		this.teacherService = teacherService;
	}

	// REST API: create a course
	@PostMapping("create")
	public ResponseEntity<Course> createCourse(@RequestBody Course course) {
		Course newCourse = courseService.createCourse(course);
		return new ResponseEntity<>(newCourse, HttpStatus.CREATED);
	}

	// REST API: get all courses
	@GetMapping()
	public ResponseEntity<List<Course>> getAllCourses() {
		List<Course> courses = courseService.getAllCourses();
		return new ResponseEntity<>(courses, HttpStatus.OK);
	}

	// REST API: get a single course using ID
	@GetMapping("{courseId}")
	public ResponseEntity<Course> getCourseById(@PathVariable("courseId") Long courseId) {
		Course course = courseService.getCourseById(courseId);
		return new ResponseEntity<>(course, HttpStatus.OK);
	}

	// REST API: update a course using ID
	@PutMapping("update/{courseId}")
	public ResponseEntity<Course> updateCourse(@PathVariable("courseId") Long courseId,
			@RequestParam(name = "teacherId", required = false) Long teacherId,
			@RequestBody(required = false) Course course) {

		course.setId(courseId);

		if (teacherId != null)
			course.setTeacher(teacherService.getTeacherById(teacherId));

		Course updatedCourse = courseService.updateCourse(course);
		return new ResponseEntity<>(updatedCourse, HttpStatus.OK);
	}

	// REST API: delete a course using ID
	@DeleteMapping("delete/{courseId}")
	public ResponseEntity<String> deleteCourse(@PathVariable("courseId") Long courseId) {
		courseService.deleteCourse(courseId);
		return new ResponseEntity<String>(
				String.format("Course with id %d is successfully deleted.", courseId),
				HttpStatus.OK);
	}

	// REST API: remove the previously assigned teacher from a course using ID
	@PostMapping("detach/{courseId}")
	public ResponseEntity<String> removeTeacherFromCourse(
			@PathVariable("courseId") Long courseId) {
		courseService.detachTeacher(courseId);
		return new ResponseEntity<String>(
				String.format("The previously assigned teacher was successfully removed from course no.: %d", courseId),
				HttpStatus.OK);

	}
}
