package com.filmmap.repository;

import com.filmmap.domain.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {

  List<Film> findByTitle(String title);

  @Query("SELECT f.id, f.title FROM film f WHERE title like %?1%")
  List<Object[]> findByTitleLike(String title);
}
