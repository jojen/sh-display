package com.jojen.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Slf4j
public class UpdateService {

    @Autowired
    DisplayService displayService;

    @Autowired
    OpenweathermapService openweathermapService;

    @Autowired
    LoxoneService loxoneService;

    @Autowired
    private Environment env;


    @PostConstruct
    @Scheduled(cron = "*/30 0 * * * ?")
    public void update() {
        log.info("update data");
        openweathermapService.update();
        loxoneService.update();
        if(!env.getActiveProfiles()[0].equals("eu")){
            displayService.update(null);
        }
    }
}
