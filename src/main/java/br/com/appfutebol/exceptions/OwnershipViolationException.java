package br.com.appfutebol.exceptions;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class OwnershipViolationException extends AbstractException {

  public OwnershipViolationException(String message) {
    super(message, BAD_REQUEST);
  }
}
