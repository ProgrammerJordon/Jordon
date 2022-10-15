package com.project.jordon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class MainController {

    @RequestMapping("/findo")
    public String index(Model model) {
        model.addAttribute("id", "test");
        return "findo";
    }

    @RequestMapping("/test")
    public String test(Model model) {
        model.addAttribute("id", "test");
        return "test";
    }


}
