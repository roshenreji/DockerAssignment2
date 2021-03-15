package com.mindtree.studentApplication.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;




@ControllerAdvice
public class GlobalExceptionHandler {

	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleGlobalException(Exception exception, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
		return new ResponseEntity(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// handle Specific Request
		@ExceptionHandler(InvalidClassroomException.class)
		public ResponseEntity<?> handleInvalidClassroomException(InvalidClassroomException exception, WebRequest request) {
			ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
			return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
		}
		
		@ExceptionHandler(InvalidStudentException.class)
		public ResponseEntity<?> handleInvalidStudentException(InvalidStudentException exception, WebRequest request) {
			ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
			return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
		}
		
		@ExceptionHandler(StudentApplicationException.class)
		public ResponseEntity<?> handleStudentApplicationException(StudentApplicationException exception, WebRequest request) {
			ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
			return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
		}
		@ExceptionHandler(StudentApplicationServiceException.class)
		public ResponseEntity<?> handleResourceNotFoundException(StudentApplicationServiceException exception, WebRequest request) {
			ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
			return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
		}
		
		// handle custom validation errors
		@ExceptionHandler(MethodArgumentNotValidException.class)
		public ResponseEntity<?> customValidationErrorHandling(MethodArgumentNotValidException exception){
			ErrorDetails errorDetails = new ErrorDetails(new Date(), "validation Error", exception.getBindingResult().getFieldError().getDefaultMessage());
			return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
		}
	
}
