package com.project.jordon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommunityBoardController {

    @RequestMapping("/community")
    public String findo_using_law(Model model) {
        model.addAttribute("session", "session");
        model.addAttribute("memberid", "memberid 님 반갑습니다.");
        return "community_board_write";
    }
}
