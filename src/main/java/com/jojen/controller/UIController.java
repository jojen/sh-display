package com.jojen.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jojen.model.TemperatureForecast;
import com.jojen.repo.TemperatureForecastRepository;
import com.jojen.repo.TemperatureRepository;
import com.jojen.service.DisplayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


/**
 * Created by JGE on 03.04.2017.
 */
@Controller
public class UIController {

    @Autowired
    TemperatureForecastRepository temperatureForecastRepository;

    @Autowired
    TemperatureRepository temperatureRepository;

    public static final String MESSAGE = "Hello World UI!";

    @Autowired
    DisplayService printService;


    @GetMapping(value = "/")
    public String home(Model model) throws IOException {
        model.addAttribute("temp", temperatureForecastRepository.findByTimeBetween(LocalDateTime.now().minusDays(3), LocalDateTime.now()));
        StringWriter writer = new StringWriter();
        new ObjectMapper().writeValue(writer, temperatureForecastRepository.findByTimeBetween(LocalDateTime.now(), LocalDateTime.now().plusDays(2)));
        model.addAttribute("tempforecast", writer.toString());
        return "home";
    }

    @RequestMapping(value = "/errorbrowser", method = RequestMethod.GET)
    public String errorbrowser() {
        return "error/error_browser";
    }
}
