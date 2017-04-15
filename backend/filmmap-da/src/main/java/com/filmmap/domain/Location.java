package com.filmmap.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "location")
public class Location extends Base {

  private String name;

  @Column(precision = 10, scale = 2)
  private Float latitude;

  @Column(precision = 10, scale = 2)
  private Float longitude;

  public Location() {

  }

  public Location(String name) {

    this.name = name;
  }

  public String getName() {

    return name;
  }

  public void setName(String name) {

    this.name = name;
  }

  public Float getLatitude() {

    return latitude;
  }

  public void setLatitude(Float latitude) {

    this.latitude = latitude;
  }

  public Float getLongitude() {

    return longitude;
  }

  public void setLongitude(Float longitude) {

    this.longitude = longitude;
  }

}
