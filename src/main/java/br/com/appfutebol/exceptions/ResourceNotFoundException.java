package br.com.appfutebol.exceptions;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends AbstractException {


  public ResourceNotFoundException(String message) {
    super(message, HttpStatus.NOT_FOUND);
  }
  public ResourceNotFoundException(String message, Object... args) {

    super(String.format(message,args), HttpStatus.NOT_FOUND);
  }
}
