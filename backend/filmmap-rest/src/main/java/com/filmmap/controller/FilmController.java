package com.filmmap.controller;

import com.filmmap.domain.Film;
import com.filmmap.repository.FilmRepository;
import com.filmmap.rest.domain.NameId;
import org.apache.commons.collections4.CollectionUtils;
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
import java.util.stream.Collectors;

@RestController
public class FilmController {

  private static final Logger LOG = LoggerFactory.getLogger(FilmController.class);

  private FilmRepository filmRepository;

  @Autowired
  public FilmController(FilmRepository filmRepository) {
    this.filmRepository = filmRepository;
  }

  @RequestMapping("/film")
  @ResponseBody
  public ResponseEntity<Film> getFilmById(@RequestParam(value = "id") Long id) {

    Film film = filmRepository.findOne(id);
    if (film != null) {
      return ResponseEntity.ok(film);
    }

    return ResponseEntity.noContent().build();
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

  @RequestMapping("/films/like")
  @ResponseBody
  public ResponseEntity<List<NameId>> getMatchFilms(@RequestParam(value = "title") String title) {

    List<Object[]> filmObjs = filmRepository.findByTitleLike(title);

    List<NameId> films = filmObjs.stream()
        .map(item -> new NameId(Long.valueOf(item[0].toString()), String.valueOf(item[1])))
        .collect(Collectors.toList());

    if (CollectionUtils.isNotEmpty(films)) {
      return ResponseEntity.ok(films);
    }
    return ResponseEntity.noContent().build();
  }

}
