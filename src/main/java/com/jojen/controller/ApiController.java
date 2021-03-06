package com.jojen.controller;


import com.jojen.model.Temperature;
import com.jojen.model.TemperatureForecast;
import com.jojen.repo.TemperatureForecastRepository;
import com.jojen.repo.TemperatureRepository;
import com.jojen.service.DisplayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;


/**
 * Created by JGE on 03.04.2017.
 */

@RequestMapping("/api")
@RestController
public class ApiController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    TemperatureForecastRepository temperatureForecastRepository;

    @Autowired
    TemperatureRepository temperatureRepository;

    @Autowired
    DisplayService displayService;

    @GetMapping(value = "/updateDisplay")
    public void updateDisplay(@RequestParam(value = "source", required = false) String source) {
        displayService.update(source);
    }

    @GetMapping(value = "/temperature")
    public List<Temperature> temperature() {
        List ret = new ArrayList();
        ret.addAll(temperatureRepository.findByTimeBetween(LocalDateTime.now().minusDays(3), LocalDateTime.now()));
        ret.addAll(temperatureForecastRepository.findByTimeBetween(LocalDateTime.now(), LocalDateTime.now().plusDays(2)));
        return ret;
    }


}
