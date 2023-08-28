package br.com.appfutebol.repositories;

import br.com.appfutebol.models.AbstractModel;
import jakarta.transaction.Transactional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@Transactional
@NoRepositoryBean
public interface AbstractRepository<T extends AbstractModel> extends JpaRepository<T, UUID> {

}
