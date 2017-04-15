package com.filmmap.controller;

import com.filmmap.domain.Location;
import com.filmmap.repository.LocationRepository;
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

@RestController
public class LocationController {

  private static final Logger LOG = LoggerFactory.getLogger(FilmController.class);

  @Autowired
  private LocationRepository locationRepository;

  @RequestMapping("/location")
  @ResponseBody
  public ResponseEntity<Location> getLocation(@RequestParam(value = "id") Long id) {

    Location location = locationRepository.findOne(id);
    return ResponseEntity.ok(location);
  }

  @RequestMapping(value = "/location", method = RequestMethod.PUT)
  @ResponseBody
  public ResponseEntity<Long> updateLocation(@RequestBody Location location) {

    Location updatedLocation = locationRepository.save(location);
    return ResponseEntity.ok(updatedLocation.getId());
  }
}
