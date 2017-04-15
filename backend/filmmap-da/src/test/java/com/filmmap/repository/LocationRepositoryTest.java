package com.filmmap.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import com.filmmap.domain.Film;
import com.filmmap.domain.Location;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class LocationRepositoryTest {

  @Autowired
  private LocationRepository locationRepository;

  @Test
  public void findById_noLocationWithIdExist_returnExpectLocation() {

    Location location = locationRepository.findOne(2L);
    assertEquals("200 block Market Street", location.getName());
  }

  @Test
  public void findById_noLocationWithIdExist_returnNull() {

    Location location = locationRepository.findOne(999999L);
    assertNull(location);
  }

}