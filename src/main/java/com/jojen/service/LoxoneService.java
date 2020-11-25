package com.jojen.service;

import com.jojen.model.LoxoneResponse;
import com.jojen.model.Temperature;
import com.jojen.repo.TemperatureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
public class LoxoneService {
    @Autowired
    TemperatureRepository temperatureRepository;

    private RestTemplate restTemplate;
    private String username;
    private String password;

    @Value("${loxone.rest.url}")
    private String url;

    private static final String TEMP_OUTSIDE = "Außen Temperatur";
    private static final String TEMP_SLEEP = "Schlafzimmer Temperatur";
    private static final String TEMP_LIVING = "Wohnzimmer Temperatur";

    private static final String DOOR_APT_SLEEPING = "Appartment Schlafzimmer Tür";
    private static final String DOOR_APT_LIVING = "Appartment Wohnzimmer Tür";
    private static final String DOOR_MAIN = "Haustüre";
    private static final String DOOR_KITCHEN_WINDOW = "Küchenfenster";
    private static final String DOOR_TERRACE = "Freisitz";

    private static final String DOOR_OLD_MAIN = "Alte Haustüre";
    private static final String DOOR_SAUNA = "Sauna Tür";
    private static final String DOOR_BASEMENT = "Keller Tür";

    @Autowired
    public LoxoneService(@Value("${loxone.rest.username}") String username,
                         @Value("${loxone.rest.password}") String password) {
        this.username = username;
        this.password = password;
        this.restTemplate = new RestTemplateBuilder()
                .setConnectTimeout(Duration.ofSeconds(1))
                .basicAuthentication(username, password)
                .build();
    }


    public void update() {
        String temp = getTemperatureOutside();
        temp = temp.replaceAll("°", "");
        temp = temp.replaceAll(" °", "");
        Temperature t = new Temperature();
        t.setTime(LocalDateTime.now());
        t.setValue(Double.parseDouble(temp));
        temperatureRepository.save(t);
    }

    public String getTemperatureOutside() {
        try {
            LoxoneResponse response = restTemplate.getForObject(getStateURL(TEMP_OUTSIDE), LoxoneResponse.class);
            return response.getValue();
        } catch (Exception e) {
            log.warn("cannot get TemperatureOutside", e);
        }
        return "";
    }

    public String getTemperatureSleeping() {
        try {
            LoxoneResponse response = restTemplate.getForObject(getStateURL(TEMP_SLEEP), LoxoneResponse.class);
            return response.getValue();
        } catch (Exception e) {
            log.warn("cannot get TemperatureSleeping", e);
        }
        return "";
    }

    public String getTemperatureLiving() {
        try {
            LoxoneResponse response = restTemplate.getForObject(getStateURL(TEMP_LIVING), LoxoneResponse.class);
            return response.getValue();
        } catch (Exception e) {
            log.warn("cannot get TemperatureLiving", e);
        }
        return "";
    }

    public List<String> getOpenDoors() {
        List<String> ret = new ArrayList<>();
        if(restTemplate.getForObject(getStateURL(DOOR_APT_SLEEPING), LoxoneResponse.class).getValue().equals("0")){
            ret.add(DOOR_APT_SLEEPING);
        }
        if(restTemplate.getForObject(getStateURL(DOOR_APT_LIVING), LoxoneResponse.class).getValue().equals("0")){
            ret.add(DOOR_APT_LIVING);
        }
        if(restTemplate.getForObject(getStateURL(DOOR_MAIN), LoxoneResponse.class).getValue().equals("0")){
            ret.add(DOOR_MAIN);
        }
        if(restTemplate.getForObject(getStateURL(DOOR_TERRACE), LoxoneResponse.class).getValue().equals("0")){
            ret.add(DOOR_TERRACE);
        }
        if(restTemplate.getForObject(getStateURL(DOOR_KITCHEN_WINDOW), LoxoneResponse.class).getValue().equals("1")){
            ret.add(DOOR_KITCHEN_WINDOW);
        }

        return ret;
    }

    private String getStateURL(String e) {
        return url+e+"/state";
    }


}
