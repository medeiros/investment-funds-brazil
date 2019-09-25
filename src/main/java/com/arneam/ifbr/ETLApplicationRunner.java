package com.arneam.ifbr;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ETLApplicationRunner {

  @Bean
  CommandLineRunner runner(ApplicationContext context, ETLConfiguration configuration) {
    return args -> {

    };
  }

}
