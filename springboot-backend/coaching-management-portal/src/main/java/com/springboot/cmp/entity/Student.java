package com.springboot.cmp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springboot.cmp.util.GenderAttributeConverter;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

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

	@Serial private static final long serialVersionUID = 1L;

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

	@Column(name = "email", length = 60, updatable = true, nullable = false)
	private String emailId;

	@Column(name = "phone_no", length = 10, updatable = true, nullable = false)
	private String phoneNo;

	@OneToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	@JoinColumn(name = "address_id")
	private Address address;

	@CreationTimestamp
	@Column(name = "date_registered")
	private java.sql.Timestamp dateRegistered;

	@UpdateTimestamp
	@Column(name = "date_updated")
	private java.sql.Timestamp dateUpdated;

	@ManyToMany(fetch = FetchType.LAZY)
	@JsonIgnore
	@JoinTable(
		name = "STUDENT_COURSE",
		joinColumns = @JoinColumn(name = "student_id"),
		inverseJoinColumns = @JoinColumn(name = "course_id")
	)
	private Set<Course> enrolledCourses;
}
