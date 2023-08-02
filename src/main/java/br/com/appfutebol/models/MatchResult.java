package br.com.appfutebol.models;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class MatchResult {

  private int goalsOfTeamOne;
  private int goalsOfTeamTwo;
}
