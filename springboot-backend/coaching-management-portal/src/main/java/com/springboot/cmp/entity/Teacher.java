package com.springboot.cmp.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springboot.cmp.util.GenderAttributeConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "TEACHER")
@DynamicInsert
@DynamicUpdate
public class Teacher implements Serializable {

	private static final long serialVersionUID = 1L;

	// @Id
	// @GenericGenerator(
	// 		name = "t_seq",
	// 		strategy = "com.springboot.cmp.generator.TeacherIdGenerator"
	// 		)
	// @GeneratedValue(generator = "t_seq")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", length = 9, updatable = false, nullable = false)
	private Long id;
	
	@Column(name = "firstName", length = 40)
	private String firstName;
	
	@Column(name = "lastName", length = 40)
	private String lastName;
	
	@Convert(converter = GenderAttributeConverter.class)
	@Column(name = "gender", length = 2, nullable = false)
	private Gender gender;
	
	@Column(name = "email", length = 100, nullable = false)
	private String emailId;
	
	@Column(name = "DOB")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate dateOfBirth;

	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "teacher")
	private List<Course> courses;
}
