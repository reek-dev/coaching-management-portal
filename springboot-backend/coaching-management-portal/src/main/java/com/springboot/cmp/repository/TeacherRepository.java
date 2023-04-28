package com.springboot.cmp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springboot.cmp.entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
	
	Optional<Teacher> findByEmailId(String email);
}
