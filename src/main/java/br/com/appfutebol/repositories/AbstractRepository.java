package br.com.appfutebol.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbstractRepository<T extends Object> extends JpaRepository<T, UUID> {

}
