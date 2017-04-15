package com.filmmap.controller;

import com.filmmap.domain.Film;
import com.filmmap.repository.FilmRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FilmController {

  private static final Logger LOG = LoggerFactory.getLogger(FilmController.class);

  @Autowired
  private FilmRepository filmRepository;

  @RequestMapping("/films")
  @ResponseBody
  public ResponseEntity<List<Film>> getFilms(@RequestParam(value = "name") String name) {

    List<Film> films = filmRepository.findByTitle(name);

    return ResponseEntity.ok(films);
  }

  @RequestMapping(value = "/film", method = RequestMethod.POST)
  public ResponseEntity<Long> createFilm(@RequestBody Film film) {

    Long filmId = -1L;
    try {
      filmId = filmRepository.save(film).getId();
    } catch (Exception e) {
      return ResponseEntity.unprocessableEntity().build();
    }
    return ResponseEntity.ok(filmId);
  }

}
