package com.project.jordon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class MainController {

    @RequestMapping("/findo")
    public String index(Model model) {
        //model.addAttribute("session", "session");
        model.addAttribute("memberid", "memberid 님 반갑습니다.");
        return "findo";
    }

    @RequestMapping("/portfolio")
    public String findo_portfolio(Model model) {
        model.addAttribute("session", "session");
        model.addAttribute("memberid", "memberid 님 반갑습니다.");
        return "findo_portfolio";
    }

    @RequestMapping("/community")
    public String findo_community(Model model) {
        model.addAttribute("session", "session");
        model.addAttribute("memberid", "memberid 님 반갑습니다.");
        return "findo_community";
    }

    @RequestMapping("/news")
    public String findo_news(Model model) {
        model.addAttribute("session", "session");
        model.addAttribute("memberid", "memberid 님 반갑습니다.");
        return "findo_news";
    }

    @RequestMapping("/login")
    public String findo_login(Model model) {
        model.addAttribute("session", "session");
        model.addAttribute("memberid", "memberid 님 반갑습니다.");
        return "findo_login";
    }

    @RequestMapping("/search")
    public String findo_search(Model model) {
        model.addAttribute("session", "session");
        model.addAttribute("memberid", "memberid 님 반갑습니다.");
        return "findo_search";
    }

    @RequestMapping("/announcement")
    public String findo_announcement(Model model) {
        model.addAttribute("session", "session");
        model.addAttribute("memberid", "memberid 님 반갑습니다.");
        return "findo_announcement";
    }
}
