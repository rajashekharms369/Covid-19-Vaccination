package com.masai.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyExceptionDetails {
	
	private LocalDateTime timestamp;
	
	private String message;
	
	private String description;
}
