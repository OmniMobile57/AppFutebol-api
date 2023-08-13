package br.com.appfutebol.api.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.util.UUID;
import lombok.Data;

@Data
public class PlayersToDrawRequest {

  @Schema(example = "a6f58889-8217-45dd-9cfc-507401b40442")
  @NotBlank(message = "Id is required")
  private UUID id;
  @Schema(example = "John Doe")
  @NotBlank(message = "Name is required")
  private String name;
  @Schema(example = "5.0", minProperties = 0, maxProperties = 5)
  @Min(0)
  @Max(5)
  @NotBlank(message = "Score is required")
  private Double score;
}
