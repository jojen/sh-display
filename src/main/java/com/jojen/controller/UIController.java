package com.jojen.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.jojen.model.TemperatureForecast;
import com.jojen.model.WeatherForecast;
import com.jojen.repo.TemperatureForecastRepository;
import com.jojen.repo.TemperatureRepository;
import com.jojen.repo.WeatherForecastRepository;
import com.jojen.service.DisplayService;
import com.jojen.service.GoogleCalendarService;
import com.jojen.service.LoxoneService;
import org.apache.tomcat.jni.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.StringWriter;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;


/**
 * Created by JGE on 03.04.2017.
 */
@Controller
public class UIController {

    @Autowired
    TemperatureForecastRepository temperatureForecastRepository;

    @Autowired
    TemperatureRepository temperatureRepository;

    @Autowired
    WeatherForecastRepository weatherForecastRepository;

    @Autowired
    GoogleCalendarService googleCalendarService;

    @Autowired
    LoxoneService loxoneService;

    public static final String MESSAGE = "Hello World UI!";

    @Autowired
    DisplayService printService;


    @GetMapping(value = "/")
    public String home(Model model) throws IOException {

        model.addAttribute("upcomingevents", googleCalendarService.getUpcomingEvents());
        model.addAttribute("upcomingtasks", googleCalendarService.getUpcomingTasks());

        List<WeatherForecast> wf = weatherForecastRepository.findByDateBetween(LocalDateTime.now().minusDays(3), LocalDateTime.now().plusDays(3));
        Collections.reverse(wf);
        model.addAttribute("weatherforecast", wf);

        model.addAttribute("opendoors", loxoneService.getOpenDoors());
        model.addAttribute("tempoutside", loxoneService.getTemperatureOutside());
        model.addAttribute("tempsleeping", loxoneService.getTemperatureSleeping());
        model.addAttribute("templiving", loxoneService.getTemperatureLiving());

        return "home";
    }

    @RequestMapping(value = "/errorbrowser", method = RequestMethod.GET)
    public String errorbrowser() {
        return "error/error_browser";
    }
}
