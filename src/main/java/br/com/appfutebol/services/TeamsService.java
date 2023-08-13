package br.com.appfutebol.services;

import br.com.appfutebol.api.requests.PlayersRequest;
import br.com.appfutebol.api.requests.PlayersToDrawRequest;
import br.com.appfutebol.api.responses.TeamsResponse;
import br.com.appfutebol.models.Players;
import java.util.List;
import java.util.UUID;

public interface TeamsService {

  List<TeamsResponse> drawTeams(List<PlayersToDrawRequest> footySpacePlayers, int amountOfTeams);
  
}
