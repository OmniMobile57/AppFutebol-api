package br.com.appfutebol.exceptions;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends AbstractException {


  public ResourceNotFoundException(String message) {
    super(message, NOT_FOUND);
  }
  public ResourceNotFoundException(String message, Object... args) {

    super(String.format(message,args), NOT_FOUND);
  }
}
