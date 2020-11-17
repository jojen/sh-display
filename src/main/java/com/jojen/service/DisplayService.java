package com.jojen.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;



/**
 * Created by JGE on 21.03.2017.
 */
@Service
@Slf4j
public class DisplayService {
    public void update() {
        ProcessBuilder builder = new ProcessBuilder();
        builder.command("sh", "-c", "update-screen.sh");
        builder.directory(new File(System.getProperty("user.home") + "/sh-display/bin"));
        try {
            builder.start();
        } catch (IOException e) {
            log.warn("cannot run update-screen.sh", e);
        }
    }
}
