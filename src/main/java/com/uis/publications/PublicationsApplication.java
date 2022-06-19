package com.uis.publications;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = { "com.uis" })
public class PublicationsApplication {

    public static void main(String[] args) {
        SpringApplication.run(PublicationsApplication.class, args);
    }

}
