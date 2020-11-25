package com.jojen.service;

import com.fasterxml.jackson.databind.JsonNode;

import com.jojen.model.TemperatureForecast;
import com.jojen.model.WeatherForecast;
import com.jojen.repo.TemperatureForecastRepository;
import com.jojen.repo.WeatherForecastRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.TimeZone;
import java.util.logging.Logger;

@Service
public class OpenweathermapService {
    RestTemplate restTemplate = new RestTemplate();

    Logger log = Logger.getLogger(OpenweathermapService.class.getName());

    @Value("${weatherforecast.APIkey}")
    private String key;

    @Value("${weatherforecast.lat}")
    private String lat;

    @Value("${weatherforecast.lon}")
    private String lon;

    @Autowired
    WeatherForecastRepository weatherForecastRepository;

    @Autowired
    TemperatureForecastRepository temperatureForecastRepository;



    public void update() {
        log.info("update weather data");
        JsonNode payload = restTemplate.getForObject("https://api.openweathermap.org/data/2.5/onecall?lat={lat}&lon={lon}&units=metric&exclude=current,minutely&appid={key}", JsonNode.class, lat, lon, key);

        for (JsonNode hour : payload.path("hourly")) {
            LocalDateTime time = getDate(hour.path("dt").asLong());
            Optional<TemperatureForecast> t = temperatureForecastRepository.findByTime(time);
            TemperatureForecast tf = new TemperatureForecast();
            if (t.isPresent()) {
                tf = t.get();
            }
            tf.setTime(time);
            tf.setValue(hour.path("temp").asDouble());
            temperatureForecastRepository.save(tf);
        }

        for (JsonNode day : payload.path("daily")) {
            LocalDateTime date = getDate(day.path("dt").asLong());
            Optional<WeatherForecast> fco = weatherForecastRepository.findByDate(date);
            WeatherForecast fc = new WeatherForecast();
            if (fco.isPresent()) {
                fc = fco.get();
            }
            fc.setDate(date);
            fc.setDayTemp(day.path("temp").path("day").asDouble());
            fc.setNightTemp(day.path("temp").path("night").asDouble());
            fc.setEveningTemp(day.path("temp").path("eve").asDouble());
            fc.setMorningTemp(day.path("temp").path("morn").asDouble());
            fc.setIcon(getIcon(day.path("weather").path(0).path("icon").asText()));
            weatherForecastRepository.save(fc);
        }



    }

    private String getIcon(String iconcode) {
        String prefix = "http://openweathermap.org/img/wn/";
        String blackIcon = iconcode;
        if (iconcode.contains("d")) {
            blackIcon = iconcode.replaceFirst("d", "n");
        }
        return prefix + blackIcon + ".png";
    }

    private LocalDateTime getDate(long timestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp),
                TimeZone.getTimeZone("UTC").toZoneId());
    }
}
