package com.jojen.controller;

import com.jojen.service.DisplayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


/**
 * Created by JGE on 03.04.2017.
 */
@Controller
public class UIController {

    public static final String MESSAGE = "Hello World UI!";

    @Autowired
    DisplayService printService;


    @GetMapping(value = "/")
    public String home(Model model) {
        return "home";
    }

    @RequestMapping(value = "/errorbrowser", method = RequestMethod.GET)
    public String errorbrowser()
    {
        return "error/error_browser";
    }
}
