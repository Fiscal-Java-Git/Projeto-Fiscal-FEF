package com.projetofef.projetofiscalfef;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com")
@EntityScan(basePackages = {"com.domains", "com.domains.enums"})
@EnableJpaRepositories(basePackages = "com.repositories")
public class ProjetoFiscalFefApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjetoFiscalFefApplication.class, args);
    }
}
