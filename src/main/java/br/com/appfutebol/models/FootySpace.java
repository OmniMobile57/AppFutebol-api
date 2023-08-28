package br.com.appfutebol.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.Data;

@Data
@Entity
public class FootySpace extends AbstractModel {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  private String name;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "footySpace")
  List<Players> player = new ArrayList<>();

  @ManyToOne
  private Person person;

  public void addPlayer(Players player) {
    player.setFootySpace(this);
    this.player.add(player);
  }

  public void setAllPlayer(List<Players> player) {
    this.player = new ArrayList<>();
    this.player.addAll(player);
  }
}
