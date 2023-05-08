package com.springboot.cmp.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "address")
@DynamicUpdate
@DynamicInsert
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    @Column(name = "street", length = 30, updatable = true)
    private String street;
    @Column(name = "city", length = 30, updatable = true)
    private String city;
    @Column(name = "state", length = 30, updatable = true)
    private String state;
    @Column(name = "zipcode", length = 6, updatable = true)
    private String zip;
}
