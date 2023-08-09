package br.com.appfutebol.mappers;

import br.com.appfutebol.api.requests.FootySpaceRequest;
import br.com.appfutebol.api.requests.PlayersRequest;
import br.com.appfutebol.api.responses.FootySpaceResponse;
import br.com.appfutebol.api.responses.PlayersResponse;
import br.com.appfutebol.models.FootySpace;
import br.com.appfutebol.models.Players;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;

public class AppMapper {

  private final ModelMapper mapper = new ModelMapper();


  public FootySpace toFootySpaceEntity(FootySpaceRequest footySpaceRequest) {
    return mapper.map(footySpaceRequest, FootySpace.class);
  }

  public FootySpaceResponse toFootySpaceResponse(FootySpace footySpace) {
    return mapper.map(footySpace, FootySpaceResponse.class);
  }

  public List<FootySpaceResponse> toFootySpaceResponseList(List<FootySpace> footySpaceList) {
    return footySpaceList.stream()
      .map(footySpace -> mapper.map(footySpace, FootySpaceResponse.class)).collect(
        Collectors.toList());
  }

  public Players toPlayerEntity(PlayersRequest playerRequest) {
    return mapper.map(playerRequest, Players.class);
  }

  public PlayersResponse toPlayerResponse(Players player) {
    return mapper.map(player, PlayersResponse.class);
  }

  public List<PlayersResponse> toPlayerResponseList(List<Players> playerList) {
    return playerList.stream()
      .map(player -> mapper.map(player, PlayersResponse.class)).collect(
        Collectors.toList());
  }

}
