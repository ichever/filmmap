package com.filmmap.repository;

import static org.junit.Assert.assertEquals;

import com.filmmap.domain.Film;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class FilmRepositoryTest {

  @Autowired
  private FilmRepository filmRepository;
  @Test
  public void getByTitlesLike_nameExist_returnExpectedFilms2() {

    List<Object[]> films = filmRepository.findByTitleLike("on");
    assertEquals(2, films.size());
  }
  @Test
  public void getByTitlesLike_nameExist_returnExpectedFilms() {

    List<Object[]> films = filmRepository.findByTitleLike("Hours");
    assertEquals(1, films.size());
    assertEquals(1L, films.get(0)[0]);
    assertEquals("St24 Hours on Craigslist", films.get(0)[1]);
  }

  @Test
  public void findByTitle_titleExist_returnOneFilmWithExpectedId() {

    List<Film> films = filmRepository.findByTitle("St24 Hours on Craigslist");
    assertEquals(Long.valueOf(1), films.get(0).getId());
  }

  @Test
  public void findByTitle_titleExist_returnOneFilm() {

    List<Film> films = filmRepository.findByTitle("St24 Hours on Craigslist");
    assertEquals(1, films.size());
  }

  @Test
  public void findByTitle_titleDoesNotExist_noFilmReturned() throws Exception {

    List<Film> films = filmRepository.findByTitle("test");

    assertEquals(0, films.size());
  }

}