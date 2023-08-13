package br.com.appfutebol.exceptions;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class NotEnoughPlayersException extends AbstractException {

  public NotEnoughPlayersException(String message, Object... args) {
    super(String.format(message,args), BAD_REQUEST);
  }
}
