package br.com.appfutebol.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class GamesPlayed {

  @Schema(example = "0")
  private int games = 0;
  @Schema(example = "0")
  private int victories = 0;
  @Schema(example = "0")
  private int draw = 0;
  @Schema(example = "0")
  private int defeat = 0;
  @Schema(example = "0")
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
