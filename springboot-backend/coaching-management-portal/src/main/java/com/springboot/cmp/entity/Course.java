package com.springboot.cmp.entity;

import java.io.Serializable;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "COURSE")
@DynamicInsert
@DynamicUpdate
public class Course implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// @Id
	// @GenericGenerator(
	// 		name = "c_seq",
	// 		strategy = "com.springboot.cmp.generator.CourseIdGenerator"
	// 		)
	// @GeneratedValue(generator = "c_seq")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", length = 5, updatable = false, nullable = false)
	private Long id;
	
	@Column(name = "name")
	private String name;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "teacher_id", nullable = true)
	private Teacher teacher;
}
