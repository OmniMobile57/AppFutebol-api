package br.com.appfutebol.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class OwnershipViolationException extends RuntimeException {


  public OwnershipViolationException(String message) {
    super(message);
  }
}
