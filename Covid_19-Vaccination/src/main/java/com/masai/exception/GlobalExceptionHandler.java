package com.masai.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	
	@ExceptionHandler(AppointmentException.class)
	public ResponseEntity<MyExceptionDetails> throwAppointmentExceptions(AppointmentException ae, WebRequest wr){
		
		MyExceptionDetails med = new MyExceptionDetails();
		
		med.setTimestamp(LocalDateTime.now());
		med.setMessage(ae.getMessage());
		med.setDescription(wr.getDescription(false));
		
		return new ResponseEntity<>(med,HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyExceptionDetails> throwAllExceptions(Exception ae, WebRequest wr){
		
		MyExceptionDetails med = new MyExceptionDetails();
		
		med.setTimestamp(LocalDateTime.now());
		med.setMessage(ae.getMessage());
		med.setDescription(wr.getDescription(false));
		
		return new ResponseEntity<>(med,HttpStatus.BAD_REQUEST);
		
	}
	
}
