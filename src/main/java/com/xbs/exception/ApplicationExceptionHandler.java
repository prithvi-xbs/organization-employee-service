package com.xbs.exception;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.xbs.dto.response.ApplicationResponse;

@ControllerAdvice
@RestController
public class ApplicationExceptionHandler {

	@ExceptionHandler(value = ApplicationException.class)
	public ResponseEntity<?> handleContentNotFoundException(ApplicationException e, HttpServletResponse response) {
		return ResponseEntity.status(e.getHttpStatus())
				.body(new ApplicationResponse<>(e.getHttpStatus().value(), e.getMessage()));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ResponseEntity<?> handleValidationError(MethodArgumentNotValidException ex) {
		BindingResult result = ex.getBindingResult();
		List<FieldError> fieldErrors = result.getFieldErrors();
		StringBuilder builder = new StringBuilder();
		for (FieldError fError : fieldErrors) {
			if (!builder.toString().isEmpty()) {
				builder.append(", ");
			}
			builder.append(fError.getDefaultMessage());
		}
		return ResponseEntity.badRequest()
				.body(new ApplicationResponse<>(HttpStatus.BAD_REQUEST.value(), builder.toString()));
	}

}
