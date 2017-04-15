package com.filmmap.util;

import static com.filmmap.util.DataIndex.ACTOR_1;
import static com.filmmap.util.DataIndex.ACTOR_2;
import static com.filmmap.util.DataIndex.ACTOR_3;
import static com.filmmap.util.DataIndex.DIRECTOR;
import static com.filmmap.util.DataIndex.DISTRIBUTOR;
import static com.filmmap.util.DataIndex.FUN_FACTS;
import static com.filmmap.util.DataIndex.LOCATIONS;
import static com.filmmap.util.DataIndex.PRODUCTION_COMPANY;
import static com.filmmap.util.DataIndex.RELEASE_YEAR;
import static com.filmmap.util.DataIndex.TITLE;
import static com.filmmap.util.DataIndex.WRITER;
import static com.google.common.collect.Maps.newHashMap;
import static com.google.common.collect.Sets.newHashSet;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

import com.filmmap.domain.Actor;
import com.filmmap.domain.Film;
import com.filmmap.domain.Location;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class DataInitializer {

  private static Logger LOG = LoggerFactory.getLogger(DataInitializer.class);

  private static final String film_data_file =
      "/home/xiaobo/sandbox/filmmap-web/src/main/resources/Film_Locations_in_San_Francisco.xlsx";

  private static String filmCreateUlr = "http://localhost:8080/film";

  public static void main(String[] args) throws IOException {

    RestTemplate restTemplate = new RestTemplate();

    Map<String, Film> filmRecords = loadFilmsFromFile();

    persistToDB(restTemplate, filmRecords);

  }

  private static void persistToDB(RestTemplate restTemplate, Map<String, Film> filmRecords) {

    filmRecords.forEach((String title, Film film) -> {
      Long filmId = restTemplate.postForObject(filmCreateUlr, film, Long.class);
      LOG.info("Job '{}' is saved with id: '{}'", film.getTitle(), filmId);
    });
  }

  private static Map<String, Film> loadFilmsFromFile() throws IOException {

    File file = new File(film_data_file);

    FileInputStream fis = new FileInputStream(file);

    XSSFWorkbook workBook = new XSSFWorkbook(fis);

    XSSFSheet sheet = workBook.getSheetAt(0);

    int maxRows = sheet.getPhysicalNumberOfRows();

    Iterator<Row> iterator = sheet.iterator();

    Map<String, Film> filmRecords = newHashMap();
    iterator.next();
    while (iterator.hasNext()) {

      Row row = iterator.next();

      String title = getStringValue(row, TITLE);
      Integer releaseYear = Integer.valueOf(getStringValue(row, RELEASE_YEAR));
      String locationStr = getStringValue(row, LOCATIONS);
      String funFacts = getStringValue(row, FUN_FACTS);
      String productionCompany = getStringValue(row, PRODUCTION_COMPANY);
      String distributor = getStringValue(row, DISTRIBUTOR);
      String director = getStringValue(row, DIRECTOR);
      String writer = getStringValue(row, WRITER);
      String actor1Str = getStringValue(row, ACTOR_1);
      String actor2Str = getStringValue(row, ACTOR_2);
      String actor3Str = getStringValue(row, ACTOR_3);

      Set<Actor> actors = newHashSet();
      addActorIfNotBlank(actor1Str, actors);
      addActorIfNotBlank(actor2Str, actors);
      addActorIfNotBlank(actor3Str, actors);

      if (isNotBlank(title)) {

        Location location = new Location(locationStr);
        if (!filmRecords.containsKey(title)) {
          Film film =
              new Film(title, releaseYear, funFacts, productionCompany, distributor, director,
                  writer, isBlank(locationStr) ? newHashSet() : newHashSet(location), actors);
          filmRecords.put(title, film);
        } else {
          if (isNotBlank(locationStr)) {
            filmRecords.get(title).addLocation(location);
          }
        }

      }
    }

    LOG.info("'{}' films are cached.", filmRecords.size());
    return filmRecords;
  }

  private static void addActorIfNotBlank(String actor1Str, Set<Actor> actors) {

    if (isNotBlank(actor1Str)) {
      actors.add(new Actor(actor1Str));
    }
  }

  private static String getStringValue(Row row, int index) {

    String value = "";
    Cell cell = row.getCell(index);
    if (cell != null) {
      cell.setCellType(CellType.STRING);
      value = cell.getRichStringCellValue().getString().trim();
    }

    return value;
  }
}
