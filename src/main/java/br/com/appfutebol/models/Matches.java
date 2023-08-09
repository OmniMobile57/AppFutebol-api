package br.com.appfutebol.models;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.util.UUID;
import lombok.Data;

@Data
@Entity
public class Matches extends AbstractModel {

  @GeneratedValue(strategy = GenerationType.UUID)
  @Id
  private UUID id;

  @ManyToOne
  private Teams teamOne;
  @ManyToOne
  private Teams teamTwo;

  @Embedded
  private MatchResult matchResult;


}
