package com.jojen.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UpdateService {

    @Autowired
    DisplayService displayService;

    @Autowired
    OpenweathermapService openweathermapService;

    @Autowired
    LoxoneService loxoneService;



    @Scheduled(cron = "0 * * * *")
    public void update() {
        log.info("update data and display");
        openweathermapService.update();
        displayService.update(null);
    }
}
