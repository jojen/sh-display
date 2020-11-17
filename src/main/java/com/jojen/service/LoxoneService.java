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

    @PostConstruct
    public void populateDefaultData() {
        LocalDateTime time = LocalDateTime.now().minusDays(3);
        while (time.isBefore(LocalDateTime.now())) {
            Temperature t = new Temperature();
            t.setTime(time);
            t.setValue(new Random().nextInt(3) + 10);
            time = time.plusHours(1);
            temperatureRepository.save(t);
        }
    }
}
