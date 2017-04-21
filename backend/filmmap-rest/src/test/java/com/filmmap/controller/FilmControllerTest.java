package com.filmmap.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

import com.filmmap.domain.Film;
import com.filmmap.repository.FilmRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RunWith(MockitoJUnitRunner.class)
public class FilmControllerTest {

  private FilmController filmController;

  @Mock
  private FilmRepository mockFilmRepository;

  @Before
  public void setup() {

    filmController = new FilmController(mockFilmRepository);
  }

  @Test
  public void getFilmById_filmDoesNotExists_returnNoContent() throws Exception {

    when(mockFilmRepository.findOne(anyLong())).thenReturn(null);
    ResponseEntity<Film> responseEntity = filmController.getFilmById(1L);
    assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
  }

  @Test
  public void createFilm_filmSaved_returnFilmId() throws Exception {

    Film film = new Film();
    when(mockFilmRepository.save(film)).thenReturn(film);
    ResponseEntity<Long> savedFilm = filmController.createFilm(film);
    assertEquals(film, savedFilm.getBody());
  }

}