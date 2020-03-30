package com.github.i0kits.app.domain.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Transient;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Department extends AbstractEntity<Long> {
  @Column(unique = true, length = 100)
  private String name;

  @Column(length = 1000)
  private String description;

  @ManyToOne
  @JoinColumn(name = "parent_id")
  private Department parent;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
  private Set<Department> children = new HashSet<>();

  @Transient
  public String getParentInfo() {
    return parent == null ? "" : String.format("%s(%s)", parent.name, parent.getId());
  }

  protected Department() {
    this(null);
  }

  private Department(Long id) {
    this.setId(id);
  }

  public Department(String name, String description) {
    this.setId(null);
    this.name = name;
    this.description = description;
  }
}
