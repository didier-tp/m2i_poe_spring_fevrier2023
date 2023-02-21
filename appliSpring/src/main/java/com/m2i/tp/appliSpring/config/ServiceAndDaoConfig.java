package com.m2i.tp.appliSpring.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = { "com.m2i.tp.appliSpring.service" })
@EnableJpaRepositories(basePackages = { "com.m2i.tp.appliSpring.dao" }) 
@EntityScan(basePackages = { "com.m2i.tp.appliSpring.entity"})
public class ServiceAndDaoConfig {
  //Cette classe correspond à une configuration d'une sous partie 
  //de l'application Spring (repository+service) mais pas la partie web/REST
}
