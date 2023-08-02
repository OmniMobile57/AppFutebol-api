package br.com.appfutebol.models;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@Data
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractModel {

  @CreatedDate
  @Hidden
  private LocalDateTime createdDate = LocalDateTime.now();
}