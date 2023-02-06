package com.uis.publications.service.impl;

import com.uis.publications.service.interfaces.IPublicationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @autor Juan David Morantes Vergara
 */
@Configuration
@ComponentScan("com.uis.publications.service.impl")

public class BeanConfig {
    @Bean
    public IPublicationService publicationService(){return new PublicationServiceImpl();}

}
