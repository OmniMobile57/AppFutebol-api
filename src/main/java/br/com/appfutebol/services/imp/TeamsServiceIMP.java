package br.com.appfutebol.services.imp;

import br.com.appfutebol.api.requests.PlayersToDrawRequest;
import br.com.appfutebol.api.responses.TeamsResponse;
import br.com.appfutebol.configs.log.AuditLog;
import br.com.appfutebol.exceptions.NotEnoughPlayersException;
import br.com.appfutebol.exceptions.ResourceNotFoundException;
import br.com.appfutebol.mappers.AppMapper;
import br.com.appfutebol.models.Players;
import br.com.appfutebol.models.Teams;
import br.com.appfutebol.repositories.PlayersRepository;
import br.com.appfutebol.services.TeamsService;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TeamsServiceIMP implements TeamsService {

  private final PlayersRepository playersRepository;
  private final AppMapper mapper = new AppMapper();

  @AuditLog(action = "Draw Teams", currentClass = "TeamsServiceIMP")
  @Override
  public List<TeamsResponse> drawTeams(List<PlayersToDrawRequest> footySpacePlayers,
    int amountOfTeams) {
    if (footySpacePlayers.size() < 4) {
      throw new NotEnoughPlayersException(
        "Not enough players, the minimum required is 4");
    }
    List<Teams> teams = formTeams(footySpacePlayers, amountOfTeams);
    return mapper.toTeamResponseList(teams);
  }

  private List<Teams> formTeams(List<PlayersToDrawRequest> playersToDrawRequests,
    int amountOfTeams) {

    List<Players> players = verifyPlayersToDrawRequest(
      playersToDrawRequests, amountOfTeams);
    List<Players> sortedPlayers = new ArrayList<>(players);
    sortedPlayers.sort(
      Comparator.comparingDouble(a -> -a.getScore())); // Ordena em ordem decrescente
    List<Teams> teams = new ArrayList<>();
    for (int i = 0; i < amountOfTeams; i++) {
      teams.add(new Teams());
    }
    for (Players player : sortedPlayers) {
      Teams teamLowestScore = teams.get(0);
      for (int j = 1; j < teams.size(); j++) {
        if (sumScore(teams.get(j)) < sumScore(teamLowestScore)) {
          teamLowestScore = teams.get(j);
        }
      }
      teamLowestScore.addPlayer(player);
    }
    return teams;
  }

  private List<Players> verifyPlayersToDrawRequest(List<PlayersToDrawRequest> playersToDrawRequests,
    int amountOfTeams) {
    int amountOfPlayers = playersToDrawRequests.size();
    if (amountOfTeams == 2 && amountOfPlayers % amountOfTeams != 0) {
      int requiredPlayers =
        amountOfPlayers + Math.abs((int) amountOfPlayers % amountOfTeams);
      throw new NotEnoughPlayersException(
        "Not enough players, the minimum required to %s groups is %s", amountOfTeams,
        requiredPlayers);
    }
    return playersToDrawRequests.stream().map(p -> {
      Players player = playersRepository.findById(p.getId())
        .orElseThrow(() -> new ResourceNotFoundException("No such player"));
      return player;
    }).collect(Collectors.toList());
  }


  private int sumScore(Teams team) {
    int sum = 0;
    for (Players player : team.getPlayer()) {
      sum += player.getScore();
    }
    return sum;
  }
}

