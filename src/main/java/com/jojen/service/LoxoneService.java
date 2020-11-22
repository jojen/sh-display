package com.jojen.service;

import com.jojen.model.Temperature;
import com.jojen.repo.TemperatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Random;

@Service
public class LoxoneService {
    @Autowired
    TemperatureRepository temperatureRepository;
    //String uri = http://10.0.0.2/dev/sps/io/Bad%20Temperatur/state;



    public void update() {

    }
}
