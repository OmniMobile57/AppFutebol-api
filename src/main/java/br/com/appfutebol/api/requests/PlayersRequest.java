package br.com.appfutebol.api.requests;

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
import java.util.UUID;
import lombok.Data;

@Data
public class PlayersRequest {

  @Schema(example = "John Doe")
  private String name;
  @Schema(example = "ADM")
  private Responsibility responsibility = Responsibility.COMMON;
  @JsonProperty("games_played")
  private GamesPlayed gamesPlayed = new GamesPlayed();
}
