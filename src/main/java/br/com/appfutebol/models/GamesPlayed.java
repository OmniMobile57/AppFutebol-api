package br.com.appfutebol.models;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class GamesPlayed {

  private int victories;
  private int draw;
  private int defeat;
  private int goals;

}
