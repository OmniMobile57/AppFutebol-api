package br.com.appfutebol.api.responses;

import br.com.appfutebol.models.GamesPlayed;
import br.com.appfutebol.models.Responsibility;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Embedded;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.util.UUID;
import lombok.Data;

@Data
public class PlayersToDrawResponse {
  @Schema(example = "a6f58889-8217-45dd-9cfc-507401b40442")
  private UUID id;
  @Schema(example = "John Doe")
  private String name;
  @Embedded
  @JsonProperty("games_played")
  private GamesPlayed gamesPlayed;
  @Schema(example = "5.0", minProperties = 0, maxProperties = 5)
  private Double score;
  @Schema(example = "ADM")
  private Responsibility responsibility = Responsibility.COMMON;
}
