package br.com.appfutebol.exceptions;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExceptionResponse implements Serializable {

  private static final long serialVersionUID = 1L;

  private Date timestamp;
  private String message;
  private String details;

}