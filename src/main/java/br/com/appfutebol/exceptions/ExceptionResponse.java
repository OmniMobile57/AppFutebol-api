package br.com.appfutebol.exceptions;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class ExceptionResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Getter
	private Date timestamp;
	@Getter
	private String message;
	@Getter
	private String details;

}