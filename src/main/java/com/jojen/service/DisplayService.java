package com.jojen.service;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;


/**
 * Created by JGE on 21.03.2017.
 */
@Service
@Slf4j
public class DisplayService {
    public void update(String source) {
        if (StringUtils.isEmpty(source)) {
            source = "http://localhost:8080/";
        }
        ProcessBuilder builder = new ProcessBuilder();
        builder.command("sh", "update-screen.sh", source);
        
        log.info("update display");
        builder.directory(new File(System.getProperty("user.home") + "/sh-display/release/bin"));
        try {
            builder.start();
        } catch (IOException e) {
            log.warn("cannot run update-screen.sh", e);
        }
    }
}
