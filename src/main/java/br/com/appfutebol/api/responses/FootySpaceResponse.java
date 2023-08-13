package br.com.appfutebol.api.responses;

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
public class FootySpaceResponse {

  @Schema(example = "a6f58889-8217-45dd-9cfc-507401b40442")
  private UUID id;
  @Schema(example = "Futebol das 20h")
  private String name;
  @JsonProperty("players")
  List<PlayersResponse> player;
}
