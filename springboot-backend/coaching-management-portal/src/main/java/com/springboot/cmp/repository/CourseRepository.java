package com.springboot.cmp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.cmp.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

	Optional<Course> findByName(String name);
}
