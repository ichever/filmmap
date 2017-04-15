package com.filmmap.domain;

import javax.persistence.Entity;

@Entity(name = "actor")
public class Actor extends Base {

  private String name;

  public Actor() {

  }

  public Actor(String name) {

    this.name = name;
  }

  public String getName() {

    return name;
  }

  public void setName(String name) {

    this.name = name;
  }

}
