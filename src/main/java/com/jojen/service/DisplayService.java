package com.jojen.service;

import com.jojen.model.Temperature;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


/**
 * Created by JGE on 21.03.2017.
 */
@Service
@Slf4j // Automatically adds a logger
public class DisplayService {

    private static final String MESSAGE = "Hello World!";

    public Temperature getSampleTemperature() {
        Temperature ret = Temperature.builder()
                .time(LocalDateTime.now())
                .value(20.0)
                .build();

        return ret;
    }

    public String doSomething(String param) {

        log.info("notice start to do something");
        return MESSAGE + " this is my param " + param;
    }
}
