package br.com.appfutebol.models;

import jakarta.persistence.CascadeType;
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
public class Person extends AbstractModel {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
  private List<Players> player = new ArrayList<>();
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
  private List<FootySpace> footySpace = new ArrayList<>();

  public void addFootySpace(FootySpace footySpace) {
    footySpace.setPerson(this);
    this.footySpace.add(footySpace);
  }

  public void addPlayer(Players player) {
    player.setPerson(this);
    this.player.add(player);
  }

  public void setAllFootySpace(List<FootySpace> footySpace) {
    this.footySpace = new ArrayList<>();
    footySpace.forEach(fs -> fs.setPerson(this));
    this.footySpace.addAll(footySpace);
  }

  public void setAllPlayer(List<Players> player) {
    this.player = new ArrayList<>();
    player.forEach(p -> p.setPerson(this));
    this.player.addAll(player);
  }


  @Override
  public String toString() {
    return "Person{" +
      "id=" + id + "}";
  }
}
