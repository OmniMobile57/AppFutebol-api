package br.com.appfutebol.util;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;

public class Utils {


  public static String getTimeFormatted(Temporal time) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy")
      .withZone(ZoneId.of("America/Sao_Paulo"));
    return formatter.format(time);
  }
}
