package br.com.appfutebol.exceptions;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExceptionResponse implements Serializable {

  private static final long serialVersionUID = 1L;

  public ExceptionResponse(String message, String details) {
    this.message = message;
    this.details = details;
    this.time = getTime();
  }

  private String time;
  private String message;
  private String details;

  public String getTime() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy")
      .withZone(ZoneId.of("America/Sao_Paulo"));
    return formatter.format(LocalDateTime.now());
  }
}