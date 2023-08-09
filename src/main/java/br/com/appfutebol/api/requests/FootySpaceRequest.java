package br.com.appfutebol.api.requests;

import br.com.appfutebol.models.AbstractModel;
import br.com.appfutebol.models.Players;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;
import java.util.UUID;
import lombok.Data;

@Data
public class FootySpaceRequest {

  @Schema(example = "Futebol das 20h")
  private String name;
  @Schema(example = "John Doe")
  @JsonProperty("player_name")
  private String playerName;

}
