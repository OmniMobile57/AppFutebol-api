package br.com.appfutebol.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.Data;

@Data
@Entity
public class Teams extends AbstractModel {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  @OneToMany
  private List<Players> player = new ArrayList<>();

  private String name;

  public void addPlayer(Players player) {
    this.player.add(player);
  }

}
