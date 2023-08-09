package br.com.appfutebol.api.responses;

import br.com.appfutebol.models.AbstractModel;
import br.com.appfutebol.models.GamesPlayed;
import br.com.appfutebol.models.Person;
import br.com.appfutebol.models.Responsibility;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import java.util.UUID;
import lombok.Data;

@Data
public class PlayersResponse {

  @Schema(example = "a6f58889-8217-45dd-9cfc-507401b40442")
  private UUID id;
  @Schema(example = "John Doe")
  private String name;
  @Embedded
  @JsonProperty("games_played")
  private GamesPlayed gamesPlayed;
  @Schema(example = "5.0")
  @Min(0)
  @Max(5)
  private Double score;
  @Schema(example = "ADM")
  private Responsibility responsibility = Responsibility.COMMON;
  @JsonIgnore
  private Person person;





}
