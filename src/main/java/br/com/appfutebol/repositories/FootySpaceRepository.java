package br.com.appfutebol.repositories;

import br.com.appfutebol.models.FootySpace;
import br.com.appfutebol.models.Person;
import java.util.List;

public interface FootySpaceRepository extends AbstractRepository<FootySpace> {

  List<FootySpace> findAllByPerson(Person person);
}
