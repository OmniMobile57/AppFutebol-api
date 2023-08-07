package br.com.appfutebol.services;

import br.com.appfutebol.api.requests.FootySpaceRequest;
import br.com.appfutebol.api.responses.FootySpaceResponse;
import br.com.appfutebol.models.FootySpace;
import java.util.List;
import java.util.UUID;

public interface FootySpaceService {

  FootySpaceResponse save(FootySpaceRequest footySpaceRequest, UUID footySpaceId, UUID personId);
  void deleteById(UUID ownerId,UUID footySpaceId);
  void leaveFootySpace(UUID playerId,UUID footySpaceId);
  FootySpaceResponse findById(UUID footySpaceId);
  List<FootySpaceResponse> findByAllByPerson(UUID personID);


}
