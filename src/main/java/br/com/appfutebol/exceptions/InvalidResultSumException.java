package br.com.appfutebol.exceptions;

import static org.springframework.http.HttpStatus.BAD_REQUEST;


public class InvalidResultSumException extends AbstractException {

  public InvalidResultSumException(String message) {
    super(message, BAD_REQUEST);
  }
}
