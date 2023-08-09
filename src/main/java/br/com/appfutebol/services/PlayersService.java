package br.com.appfutebol.services;

import br.com.appfutebol.api.requests.PlayersRequest;
import br.com.appfutebol.api.responses.PlayersResponse;
import java.util.List;
import java.util.UUID;

public interface PlayersService {

  PlayersResponse save(PlayersRequest playersRequest, UUID footySpaceId, UUID playerId, UUID personId);

  void deletePlayer(UUID playerID);

  List<PlayersResponse> findAllByFootySpace(UUID footySpaceId);

  List<PlayersResponse> findAllByNameAndFootySpace(String name, UUID footySpaceId);


}
