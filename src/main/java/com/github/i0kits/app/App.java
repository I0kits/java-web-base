package com.github.i0kits.app;

import com.github.i0kits.app.domain.entities.DepartmentRepository;
import com.github.i0kits.app.domain.entities.Department;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

  @Bean
  public CommandLineRunner test(DepartmentRepository repo) {
    return (args) -> {
      Department root = repo.save(new Department("阿里巴巴", "集团公司"));
      log.debug("the root dept created: {}", root);

      Department d1 = new Department("淘宝", "C2C电商事业部");
      d1.setParent(root);
      Department d2 = new Department("天猫", "B2B电商事业部");
      d2.setParent(root);
      Department d3 = new Department("阿里云", "云计算事业部");
      d3.setParent(root);

      repo.save(d1);
      log.debug("the d1 id: {}", d1.getId());

      repo.save(d2);
      repo.save(d3);

      repo.findAll().forEach(department -> {
        log.info("{}\n--------------------------------", department);
      });
    };
  }
}
