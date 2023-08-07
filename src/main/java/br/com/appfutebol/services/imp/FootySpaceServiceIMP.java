package br.com.appfutebol.services.imp;

import static br.com.appfutebol.models.Responsibility.ADM;
import static br.com.appfutebol.models.Responsibility.OWNER;

import br.com.appfutebol.api.requests.FootySpaceRequest;
import br.com.appfutebol.api.responses.FootySpaceResponse;
import br.com.appfutebol.configs.log.AuditLog;
import br.com.appfutebol.exceptions.OwnershipViolationException;
import br.com.appfutebol.exceptions.ResourceNotFoundException;
import br.com.appfutebol.mappers.AppMapper;
import br.com.appfutebol.models.FootySpace;
import br.com.appfutebol.models.Person;
import br.com.appfutebol.models.Players;
import br.com.appfutebol.models.Responsibility;
import br.com.appfutebol.repositories.FootySpaceRepository;
import br.com.appfutebol.repositories.PersonRepository;
import br.com.appfutebol.repositories.PlayersRepository;
import br.com.appfutebol.services.FootySpaceService;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FootySpaceServiceIMP implements FootySpaceService {

  private final FootySpaceRepository footySpaceRepository;
  private final PersonRepository personRepository;
  private final PlayersRepository playerRepository;
  private final AppMapper mapper = new AppMapper();

  @AuditLog(action = "Save FootySpace", currentClass = "FootySpaceServiceIMP")
  @Override
  public FootySpaceResponse save(FootySpaceRequest footySpaceRequest, UUID footySpaceId,
    UUID personId) {
    if (Objects.nonNull(footySpaceId)) {
      AtomicReference<FootySpace> updatedFootySpace = new AtomicReference<>();
      footySpaceRepository.findById(footySpaceId).ifPresentOrElse(footySpace -> {
        footySpace.setName(footySpaceRequest.getName());
        updatedFootySpace.set(footySpaceRepository.save(footySpace));
      }, () -> {
        throw new ResourceNotFoundException("No such FootySpace");
      });
      return mapper.toFootySpaceResponse(updatedFootySpace.get());
    }
    if (Objects.nonNull(personId)) {
      Person person = personRepository.findById(personId)
        .orElseThrow(() -> new ResourceNotFoundException("No such person"));
      FootySpace footySpace =
        mapper.toFootySpaceEntity(footySpaceRequest);
      Players player = new Players();
      player.setResponsibility(OWNER);
      player.setName(footySpaceRequest.getPlayerName());

      person.addFootySpace(footySpace);
      person.addPlayer(player);
      personRepository.save(person);
      return mapper.toFootySpaceResponse(footySpace);
    }
    Person person = new Person();
    Players player = new Players();
    player.setResponsibility(OWNER);
    player.setName(UUID.randomUUID().toString());
    FootySpace footySpaceEntity = mapper.toFootySpaceEntity(footySpaceRequest);
    footySpaceEntity.addPlayer(player);
    person.addFootySpace(footySpaceEntity);
    person.addPlayer(player);
    personRepository.save(person);

    return mapper.toFootySpaceResponse(footySpaceEntity);
  }

  @AuditLog(action = "Delete FootySpace By Id", currentClass = "FootySpaceServiceIMP")
  @Override
  public void deleteById(UUID ownerId, UUID footySpaceId) {
    FootySpace footySpace = footySpaceRepository.findById(footySpaceId)
      .orElseThrow(() -> new ResourceNotFoundException("No such FootySpace"));
    personRepository.findById(ownerId).ifPresentOrElse(person -> {
//      List<Players> footySpaceOwner = footySpace.getPlayer();

      List<Players> ownersList = person.getPlayer().stream()
        .filter(player -> player.getResponsibility().equals(OWNER)).collect(
          Collectors.toList());

      // Lista de jogadores dono do grupo
      List<Players> footySpaceOwner = footySpace.getPlayer().stream()
        .filter(player -> player.getResponsibility()
          .equals(OWNER)).collect(Collectors.toList()).stream().toList();

      // vendo se algum jogador dono do usuário é o mesmo que o jogador dono do grupo
      boolean isOwner = ownersList.stream().anyMatch(player -> footySpaceOwner.contains(player));

      if (isOwner) {
        footySpaceRepository.deleteById(footySpaceId);
      } else {
        throw new OwnershipViolationException("Cannot delete, person is not the owner");
      }
    }, () -> {
      throw new ResourceNotFoundException("No such person");
    });
  }

  @AuditLog(action = "Leave FootySpace", currentClass = "FootySpaceServiceIMP")
  @Override
  public void leaveFootySpace(UUID playerId, UUID footySpaceId) {
    FootySpace footySpace = footySpaceRepository.findById(footySpaceId)
      .orElseThrow(() -> new ResourceNotFoundException("No such footy space"));
    Players player = playerRepository.findById(playerId)
      .orElseThrow(() -> new ResourceNotFoundException("No such player"));

    Person person = player.getPerson();
    if (player.getResponsibility().equals(OWNER)) {
      boolean anyADMExists = footySpace.getPlayer().stream()
        .anyMatch(p -> p.getResponsibility().equals(Responsibility.ADM));
      if (anyADMExists) {
        giveOwnerToAnyADMPlayerInTheFootySpace(footySpace);
        leaveFootySpace(footySpace, person, player);

      } else {
        giveOwnerToAnyPlayerInTheFootySpace(footySpace);
        leaveFootySpace(footySpace, person, player);
      }
      return;
    }
    leaveFootySpace(footySpace, person, player);

  }


  @AuditLog(action = "Find FootySpace By Id", currentClass = "FootySpaceServiceIMP")
  @Override
  public FootySpaceResponse findById(UUID footySpaceId) {
    FootySpace footySpace = footySpaceRepository.findById(footySpaceId)
      .orElseThrow(() -> new ResourceNotFoundException(("No such FootySpace")));
    return mapper.toFootySpaceResponse(footySpace);
  }

  @AuditLog(action = "Find All FootySpace By Person", currentClass = "FootySpaceServiceIMP")
  @Override
  public List<FootySpaceResponse> findByAllByPerson(UUID personID) {
    Person person = personRepository.findById(personID)
      .orElseThrow(() -> new ResourceNotFoundException("No such person"));
    return mapper.toFootySpaceResponseList(footySpaceRepository.findAllByPerson(person));
  }

  private void leaveFootySpace(FootySpace footySpace, Person person, Players player) {
    List<Players> playersList = footySpace.getPlayer().stream()
      .filter(p -> !p.getId().equals(player.getId()))
      .collect(Collectors.toList());
    List<FootySpace> footySpaceList = person.getFootySpace().stream()
      .filter(fs -> !fs.getName().equals(footySpace.getName())).collect(Collectors.toList());

    person.setAllFootySpace(footySpaceList);
    footySpace.setAllPlayer(playersList);

    footySpaceRepository.save(footySpace);
    personRepository.save(person);
  }

  private void giveOwnerToAnyPlayerInTheFootySpace(FootySpace footySpace) {
    List<Players> playersList = footySpace.getPlayer();

    int index = (int) Math.random() * (playersList.size() - 1);
    if (index > 0) {
      Players player = playersList.get(index);
      player.setResponsibility(OWNER);
      playerRepository.save(player);
    }
  }

  private void giveOwnerToAnyADMPlayerInTheFootySpace(FootySpace footySpace) {
    List<Players> playersList = footySpace.getPlayer().stream()
      .filter(player -> player.getResponsibility().equals(ADM)).collect(Collectors.toList());
    int index = (int) Math.random() * (playersList.size() - 1);
    if (index > 0) {
      Players player = playersList.get(index);
      player.setResponsibility(OWNER);
      playerRepository.save(player);
    }
  }
}