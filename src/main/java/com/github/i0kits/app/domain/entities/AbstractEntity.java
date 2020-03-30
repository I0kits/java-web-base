package com.github.i0kits.app.domain.entities;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Version;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
public class AbstractEntity<PK extends Serializable> extends AbstractPersistable<PK> {
  @Version
  @LastModifiedDate
  @Column(columnDefinition = "TIMESTAMP")
  private LocalDateTime lastModifiedTime;

  @CreatedDate
  @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
  private LocalDateTime createdTime;
}
