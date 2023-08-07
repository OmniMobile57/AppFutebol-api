package br.com.appfutebol.models;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class GamesPlayed {

  private int victories = 0;
  private int draw = 0;
  private int defeat = 0;
  private int goals = 0;

}
