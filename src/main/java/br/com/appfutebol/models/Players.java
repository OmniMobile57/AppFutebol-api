package br.com.appfutebol.models;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.text.DecimalFormat;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Players extends AbstractModel {

  private static final Double VICTORY_WEIGHT = 3D;
  private static final Double DRAW_WEIGHT = 1D;
  private static final Double GOAL_WEIGHT = 1D;
  private static final Double MAX_SCORE = 5D;
  private static final Double MIN_SCORE = 0D;
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  private String name;
  @Embedded
  private GamesPlayed gamesPlayed = new GamesPlayed();
  private Double score = 0D;
  @Enumerated(EnumType.STRING)
  private Responsibility responsibility = Responsibility.COMMON;
  @ManyToOne
  private FootySpace footySpace;

  @ManyToOne
  private Person person;

  public void setScore() {
    DecimalFormat decimalFormat = new DecimalFormat("#.#");

    int victories = this.gamesPlayed.getVictories();
    int draw = this.gamesPlayed.getDraw();
    int goals = this.gamesPlayed.getGoals();
    int games = this.gamesPlayed.getGames();

    double victoryRatio = Double.valueOf(decimalFormat.format((double) victories / games).replace(",", "."));
    double drawRatio = Double.valueOf(decimalFormat.format((double) draw / games).replace(",", "."));
    double goalRatio = Double.valueOf(decimalFormat.format((double) goals / games).replace(",", "."));

    double score = (victoryRatio * VICTORY_WEIGHT) + (drawRatio * DRAW_WEIGHT) + (goalRatio * GOAL_WEIGHT);

    this.score = Math.min(MAX_SCORE, Math.max(MIN_SCORE, score));
  }

}
