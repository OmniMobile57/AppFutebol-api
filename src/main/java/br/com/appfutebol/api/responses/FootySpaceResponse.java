package br.com.appfutebol.api.responses;

import br.com.appfutebol.models.Person;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
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
  @JsonIncludeProperties("id")
  @Schema(example = """
    {
      "id": "a6f58889-8217-45dd-9cfc-507401b40442"
    }
    """)
  Person person;
}
