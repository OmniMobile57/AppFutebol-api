package br.com.appfutebol.services.imp;

import static br.com.appfutebol.models.Responsibility.OWNER;

import br.com.appfutebol.api.requests.PlayersRequest;
import br.com.appfutebol.api.responses.PlayersResponse;
import br.com.appfutebol.configs.log.AuditLog;
import br.com.appfutebol.exceptions.InvalidResultSumException;
import br.com.appfutebol.exceptions.NotEnoughPlayersException;
import br.com.appfutebol.exceptions.OwnershipViolationException;
import br.com.appfutebol.exceptions.ResourceNotFoundException;
import br.com.appfutebol.mappers.AppMapper;
import br.com.appfutebol.models.FootySpace;
import br.com.appfutebol.models.Players;
import br.com.appfutebol.repositories.FootySpaceRepository;
import br.com.appfutebol.repositories.PersonRepository;
import br.com.appfutebol.repositories.PlayersRepository;
import br.com.appfutebol.services.PlayersService;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PlayersServiceIMP implements PlayersService {

  private final PlayersRepository playersRepository;
  private final FootySpaceRepository footySpaceRepository;
  private final PersonRepository personRepository;
  private final AppMapper mapper = new AppMapper();

  @AuditLog(action = "Save players", currentClass = "PlayerServiceIMP")
  @Override
  public PlayersResponse save(PlayersRequest playersRequest, UUID footySpaceId, UUID playerId,
    UUID personId) {
    FootySpace footySpace = footySpaceRepository.findById(footySpaceId)
      .orElseThrow(() -> new ResourceNotFoundException("No such footy space"));
    if (Objects.nonNull(playerId)) {
      Players player = playersRepository.findById(playerId)
        .orElseThrow(() -> new ResourceNotFoundException("No such player"));
      player.setName(playersRequest.getName());
      setResponsibility(playersRequest, player);
      return mapper.toPlayerResponse(playersRepository.save(player));
    }
    Players player = mapper.toPlayerEntity(playersRequest);
    setResponsibility(playersRequest, player);
    footySpace.addPlayer(player);
    if (Objects.nonNull(personId)) {
      personRepository.findById(personId).ifPresentOrElse(person -> {
        person.addPlayer(player);
        person.addFootySpace(footySpace);
        personRepository.save(person);
      }, () -> new ResourceNotFoundException("No such person"));
    }
    footySpaceRepository.save(footySpace);
    return mapper.toPlayerResponse(player);
  }

  @Override
  @AuditLog(action = "Save New Games Played", currentClass = "PlayerServiceIMP")
  public List<PlayersResponse> saveGamesPlayed(List<PlayersRequest> playersRequests) {
    return setGamesPlayed(playersRequests);
  }

  @AuditLog(action = "Deleting players", currentClass = "PlayerServiceIMP")
  @Override
  public void deletePlayer(UUID playerID) {
    playersRepository.deleteById(playerID);
  }

  @AuditLog(action = "Find all players by FootySpace", currentClass = "PlayerServiceIMP")
  @Override
  public List<PlayersResponse> findAllByFootySpace(UUID footySpaceId) {
    List<PlayersResponse> playersResponses = mapper.toPlayerResponseList(
      playersRepository.findByFootySpace(footySpaceId));
    return playersResponses;
  }

  @AuditLog(action = "Find all players by name and FootySpace", currentClass = "PlayerServiceIMP")
  @Override
  public List<PlayersResponse> findAllByNameAndFootySpace(String name, UUID footySpaceId) {
    return playersRepository.findByNameAndFootySpace(name, footySpaceId);
  }

  private void setResponsibility(PlayersRequest playersRequest, Players player) {
    if (playersRequest.getResponsibility().equals(OWNER)) {
      throw new OwnershipViolationException(
        "It is not possible to have two owners in a footy space");
    }
    player.setResponsibility(playersRequest.getResponsibility());
  }

  private List<PlayersResponse> setGamesPlayed(List<PlayersRequest> playersRequest) {
    if (playersRequest.isEmpty()) {
      throw new NotEnoughPlayersException("Unable to update players. List is empty");
    }
    List<Players> playersList = playersRepository.findAllById(
      playersRequest.stream().map(p -> p.getId()).collect(Collectors.toList()));

    for (int i = 0; i < playersList.size(); i++) {

      int games = playersRequest.get(i).getGamesPlayed().getGames();
      int defeat = playersRequest.get(i).getGamesPlayed().getDefeat();
      int goals = playersRequest.get(i).getGamesPlayed().getGoals();
      int draw = playersRequest.get(i).getGamesPlayed().getDraw();
      int victories = playersRequest.get(i).getGamesPlayed().getVictories();

      if (games != (defeat + draw + victories)) {
        throw new InvalidResultSumException(
          "The sum of 'victories', 'draws' and 'defeats' must be the amount of 'games'");
      }
      playersList.get(i).getGamesPlayed().setGames(games);
      playersList.get(i).getGamesPlayed().setDraw(draw);
      playersList.get(i).getGamesPlayed().setDefeat(defeat);
      playersList.get(i).getGamesPlayed().setGoals(goals);
      playersList.get(i).getGamesPlayed().setVictories(victories);

      playersList.get(i).setScore();
    }
    return mapper.toPlayerResponseList(playersRepository.saveAll(playersList));
  }

}
