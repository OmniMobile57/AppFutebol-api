package br.com.appfutebol.api.requests;

import br.com.appfutebol.models.AbstractModel;
import br.com.appfutebol.models.Players;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;
import java.util.UUID;
import lombok.Data;

@Data
public class TeamsRequest {

  @NotEmpty(message = "Player list cannot be empty")
  private List<PlayersRequest> player;

}
