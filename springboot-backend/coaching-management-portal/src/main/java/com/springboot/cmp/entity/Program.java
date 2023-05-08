package com.springboot.cmp.entity;

import java.io.Serial;
import java.io.Serializable;



public class Program implements Serializable {

	@Serial private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private Integer duration;

}
