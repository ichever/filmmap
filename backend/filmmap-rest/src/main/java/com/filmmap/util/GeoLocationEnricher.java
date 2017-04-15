package com.filmmap.util;

import static java.lang.String.format;

import com.filmmap.domain.Location;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.util.stream.LongStream;

public class GeoLocationEnricher {

  private static final Logger LOG = LoggerFactory.getLogger(GeoLocationEnricher.class);

  private static final String LOCATION_GET_URL_FORMAT = "http://localhost:8080/location?id=%s";

  private static final String LOCATION_PUT_URL_FORMAT = "http://localhost:8080/location";

  private static final String GOOGLE_MAPS_URL_FORMAT =
      "https://maps.googleapis.com/maps/api/geocode/json?&address=%s";

  private static final String SF_SURFIX = "San Francisco, CA, USA";

  private String googleMapsApiKey = "";

  public static void main(String[] args) {

    RestTemplate restTemplate = new RestTemplate();

    LongStream.range(1, 1540).forEach(id -> {

      sleep();

      Location location =
          restTemplate.getForObject(format(LOCATION_GET_URL_FORMAT, id), Location.class);

      if (location != null && (location.getLatitude() == null || location.getLongitude() == null)) {
        String shortLocationName = location.getName();
        String fullLocationLocation = format("%s, %s", shortLocationName, SF_SURFIX);
        String googleMapsApi = format(GOOGLE_MAPS_URL_FORMAT, fullLocationLocation);
        String response = restTemplate.getForObject(googleMapsApi, String.class);

        LOG.info("Response for '{}' is \n'{}'", fullLocationLocation, response);

        enrichGeometryToLocation(location, response);

        try {
          restTemplate.put(LOCATION_PUT_URL_FORMAT, location);
          LOG.info("Persist ");
        } catch (Exception e) {
          LOG.error("Failed to update geometry for location id:'{}', name:'{}'", location.getId(),
              location.getName());
          e.printStackTrace();
        }

      }
    });

  }

  private static void sleep() {

    try {
      Thread.sleep(200L);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private static void enrichGeometryToLocation(Location location, String response) {

    try {
      JsonObject asJsonObject = new JsonParser().parse(response).getAsJsonObject();

      String status = asJsonObject.get("status").getAsString();

      if (status.equalsIgnoreCase("ZERO_RESULTS")) {

        LOG.error("No geometry info found from Google maps api: '{}', response: '{}'",
            location.getName(), asJsonObject.getAsString());

      } else if (status.equalsIgnoreCase("OK")) {
        JsonObject geometry =
            asJsonObject.get("results").getAsJsonArray().get(0).getAsJsonObject().get("geometry")
                .getAsJsonObject();
        JsonObject geoLocation = geometry.get("location").getAsJsonObject();
        Float latitude = geoLocation.get("lat").getAsFloat();
        Float longitude = geoLocation.get("lng").getAsFloat();
        LOG.info("Latitude is {}", latitude);
        LOG.info("Longitude is {}", longitude);
        location.setLatitude(latitude);
        location.setLongitude(longitude);
      }

    } catch (Exception e) {
      e.printStackTrace();
      LOG.error("failed to get geometry info for location: '{}'", location.getName());
    }
  }
}
