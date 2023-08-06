package br.com.appfutebol.api.responses;

import br.com.appfutebol.models.AbstractModel;
import br.com.appfutebol.models.Players;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;
import java.util.UUID;
import lombok.Data;

@Data
public class FootySpaceResponse {

  private UUID id;
  private String name;
  @JsonProperty("players")
  List<PlayersResponse> player;
}
