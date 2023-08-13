package br.com.appfutebol.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AbstractException extends RuntimeException {


  HttpStatus status;

  public AbstractException(String message, HttpStatus status) {
    super(message);
    this.status = status;
  }
}
