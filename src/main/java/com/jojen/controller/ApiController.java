package com.jojen.controller;


import com.jojen.model.Temperature;
import com.jojen.service.DisplayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * Created by JGE on 03.04.2017.
 */

@RequestMapping("/api")
@RestController
public class ApiController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    DisplayService displayService;


    @GetMapping(value = "/data")
    public void data()  {
    }


}
