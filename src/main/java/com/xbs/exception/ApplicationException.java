package com.xbs.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String message;

	private HttpStatus httpStatus;

	public ApplicationException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public ApplicationException(HttpStatus httpStatus, String message) {
		this.httpStatus = httpStatus;
		this.message = message;
	}

}
