package br.com.appfutebol.api.responses;

import br.com.appfutebol.models.AbstractModel;
import br.com.appfutebol.models.Players;
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
public class TeamsResponse {
  private List<PlayersToDrawResponse> player;

}
