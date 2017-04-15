package com.filmmap.bootstrap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ComponentScan(basePackages = "com.filmmap")
@ImportResource({"classpath:filmmap-context.xml"})
public class FilmMapApplication {

  private static final Logger log = LoggerFactory.getLogger(FilmMapApplication.class);

  public static void main(String[] args) {

    SpringApplication.run(FilmMapApplication.class, args);
  }
}
