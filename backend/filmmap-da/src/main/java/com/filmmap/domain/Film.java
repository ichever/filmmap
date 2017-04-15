package com.filmmap.domain;

import static com.google.common.collect.Sets.newHashSet;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity(name = "film")
public class Film extends Base {

  private String title;

  @Column(name = "release_year")
  private Integer releaseYear;

  @Column(name = "fun_facts")
  private String funFacts;

  @Column(name = "production_company")
  private String productionCompany;

  private String distributor;

  private String director;

  private String writer;

  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinTable(name = "film_location", joinColumns = {
      @JoinColumn(name = "film_id", nullable = false)}, inverseJoinColumns = {
      @JoinColumn(name = "location_id", nullable = false)})
  private Set<Location> locations;

  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinTable(name = "film_actor", joinColumns = {
      @JoinColumn(name = "film_id", nullable = false)}, inverseJoinColumns = {
      @JoinColumn(name = "actor_id", nullable = false)})
  private Set<Actor> actors;

  public Film() {

  }

  public Film(String title, Integer releaseYear, String funFacts, String productionCompany,
      String distributor, String director, String writer, Set<Location> locations,
      Set<Actor> actors) {

    this.title = title;
    this.releaseYear = releaseYear;
    this.funFacts = funFacts;
    this.productionCompany = productionCompany;
    this.distributor = distributor;
    this.director = director;
    this.writer = writer;
    this.locations = locations;
    this.actors = actors;
  }

  public void addLocation(Location... locations) {

    this.getLocations().addAll(newHashSet(locations));
  }

  public String getTitle() {

    return title;
  }

  public void setTitle(String title) {

    this.title = title;
  }

  public Integer getReleaseYear() {

    return releaseYear;
  }

  public void setReleaseYear(Integer releaseYear) {

    this.releaseYear = releaseYear;
  }

  public String getFunFacts() {

    return funFacts;
  }

  public void setFunFacts(String funFacts) {

    this.funFacts = funFacts;
  }

  public String getProductionCompany() {

    return productionCompany;
  }

  public void setProductionCompany(String productionCompany) {

    this.productionCompany = productionCompany;
  }

  public String getDistributor() {

    return distributor;
  }

  public void setDistributor(String distributor) {

    this.distributor = distributor;
  }

  public String getDirector() {

    return director;
  }

  public void setDirector(String director) {

    this.director = director;
  }

  public String getWriter() {

    return writer;
  }

  public void setWriter(String writer) {

    this.writer = writer;
  }

  public Set<Location> getLocations() {

    return locations;
  }

  public void setLocations(Set<Location> locations) {

    this.locations = locations;
  }

  public Set<Actor> getActors() {

    return actors;
  }

  public void setActors(Set<Actor> actors) {

    this.actors = actors;
  }
}
