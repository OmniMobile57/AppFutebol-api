package br.com.appfutebol.models;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class GamesPlayed {

  private int games = 0;
  private int victories = 0;
  private int draw = 0;
  private int defeat = 0;
  private int goals = 0;


  public void setGames(int games) {
    this.games += games;
  }

  public void setVictories(int victories) {
    this.victories += victories;
  }

  public void setDraw(int draw) {
    this.draw += draw;
  }

  public void setDefeat(int defeat) {
    this.defeat += defeat;
  }

  public void setGoals(int goals) {
    this.goals += goals;
  }


}
