package br.com.appfutebol.models;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.util.UUID;
import lombok.Data;

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


}
