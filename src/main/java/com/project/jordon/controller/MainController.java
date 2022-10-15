package com.project.jordon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("/findo")
    public String index() {
        return "findo";
    }


}
