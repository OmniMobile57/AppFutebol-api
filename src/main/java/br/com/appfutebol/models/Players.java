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

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  private String name;
  @Embedded
  private GamesPlayed gamesPlayed = new GamesPlayed();
  private Double score = 0.0;
  //  @Enumerated(EnumType.STRING)
  //  private Position position;
  @Enumerated(EnumType.STRING)
  private Responsibility responsibility = Responsibility.COMMON;
  @ManyToOne
  private FootySpace footySpace;

  @ManyToOne
  private Person person;

  public void setScore() {
    double maxPointsActual = this.score * this.gamesPlayed.getGames();
    double victoryScore = this.gamesPlayed.getVictories();
    double drawScore = this.gamesPlayed.getDraw();
    double goalScore = this.gamesPlayed.getGoals();

    double newMaxPoints = maxPointsActual + victoryScore + drawScore + goalScore;
    DecimalFormat decimalFormat = new DecimalFormat("#.#");

    double newScore = Double.valueOf(decimalFormat.format(newMaxPoints / this.gamesPlayed.getGames()).replace(",", "."));
    this.setScore(newScore);

  }

}
