package com.hm.productclaim.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDTO {

	private String message;
	private Object data;
	private int statusCode;
	
	public ResponseDTO(int statusCode, String message) {
		this.statusCode = statusCode;
		this.message = message;
	}

	public ResponseDTO(int statusCode, String message, Object data) {
		this.message = message;
		this.data = data;
		this.statusCode = statusCode;
	}
}