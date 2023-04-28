package com.springboot.cmp.exceptions;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionDetail {
	private LocalDateTime timestamp;
	private String message;
	private String path;
	private String errorCode;
}
