package com.github.i0kits.app.domain.entities;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.data.rest.core.config.Projection;

import java.awt.print.Pageable;

@Projection(name = "default", types = Department.class)
interface DepartmentProjection {
  long getId();
  String getName();
  String getParentInfo();
  String getDescription();

  @Value("#{target.parent == null}")
  boolean getIsRoot();
}

@RepositoryRestResource(path = "departments", excerptProjection = DepartmentProjection.class)
public interface DepartmentRepository extends PagingAndSortingRepository<Department, Long> {
  @RestResource(exported = false)
  @Override
  void delete(Department entity);

  @RestResource(exported = false)
  @Override
  void deleteAll(Iterable<? extends Department> entities);

  @RestResource(exported = false)
  @Override
  void deleteById(Long aLong);

  @RestResource(path = "name", rel = "name")
  Department findByName(@Param("name") String name);

  //@RestResource(path = "nameStartsWith", rel = "nameStartsWith")
  //Page findByNameStartsWith(@Param("name") String name, Pageable pageable);
}
