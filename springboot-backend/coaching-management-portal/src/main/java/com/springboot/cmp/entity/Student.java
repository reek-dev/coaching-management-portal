package com.springboot.cmp.entity;

import java.io.Serializable;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.springboot.cmp.util.GenderAttributeConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
@Table(name = "STUDENT")
@DynamicInsert
@DynamicUpdate
public class Student implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", length = 9, updatable = false, nullable = false)
	private Long id;

	@Column(name = "first_name", length = 40, updatable = true)
	private String firstName;

	@Column(name = "last_name", length = 40, updatable = true)
	private String lastName;

	@Convert(converter = GenderAttributeConverter.class)
	@Column(name = "gender", length = 2, updatable = true, nullable = false)
	private Gender gender;

	@Column(name = "phone_no", length = 10, updatable = true, nullable = false)
	private String phoneNo;

	@CreationTimestamp
	@Column(name = "date_registered")
	private java.sql.Timestamp dateRegistered;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
		name = "STUDENT_TEACHER",
		joinColumns = @JoinColumn(name = "student_id"),
		inverseJoinColumns = @JoinColumn(name = "teacher_id")
	)
	private List<Teacher> teachers;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
		name = "STUDENT_COURSE",
		joinColumns = @JoinColumn(name = "student_id"),
		inverseJoinColumns = @JoinColumn(name = "course_id")
	)
	private List<Course> courses;

}
