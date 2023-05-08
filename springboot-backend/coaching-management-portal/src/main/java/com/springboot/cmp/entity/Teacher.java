package com.springboot.cmp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springboot.cmp.util.GenderAttributeConverter;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

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

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id", referencedColumnName = "id")
	private Address address;

	@CreationTimestamp
	@Column(name = "date_registered")
	private java.sql.Timestamp dateRegistered;

	@UpdateTimestamp
	@Column(name = "date_updated")
	private java.sql.Timestamp dateUpdated;
	
	@Column(name = "email", length = 100, nullable = false)
	private String emailId;
	
	@Column(name = "DOB")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate dateOfBirth;

	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "teacher")
	private Set<Course> courses;
}
