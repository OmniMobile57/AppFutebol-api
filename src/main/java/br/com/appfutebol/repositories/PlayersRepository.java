package br.com.appfutebol.repositories;

import br.com.appfutebol.api.responses.PlayersResponse;
import br.com.appfutebol.models.Players;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.Query;

public interface PlayersRepository extends AbstractRepository<Players> {

  @Query("SELECT p FROM Players p WHERE p.footySpace.id =:footySpaceId")
  List<Players> findByFootySpace(UUID footySpaceId);

  @Query("SELECT p FROM Players p WHERE p.name LIKE %:name% AND p.footySpace.id =:footySpaceId")
  List<PlayersResponse> findByNameAndFootySpace(String name, UUID footySpaceId);
}
