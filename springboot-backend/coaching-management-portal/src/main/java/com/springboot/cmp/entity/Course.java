package com.springboot.cmp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;

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

	@Column(name = "credits", length = 2, nullable = false)
	private Integer credits;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "teacher_id", nullable = true)
	private Teacher teacher;
}
