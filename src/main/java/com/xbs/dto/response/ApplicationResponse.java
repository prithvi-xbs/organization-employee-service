package com.xbs.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Generic Organization Response
 * 
 * @param <T>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationResponse<T> {

	private T data;

	private int code;

	private String message;

	public ApplicationResponse(int code, String message) {
		this.code = code;
		this.message = message;
	}
}
