package com.project.jordon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommunityBoardController {

    @RequestMapping("/community_board_write")
    public String community_board_write(Model model) {
        model.addAttribute("session", "session");
        model.addAttribute("memberid", "memberid 님 반갑습니다.");
        return "community_board_write";
    }
    @RequestMapping("/community_board_write_ok")
    public String community_board_write_ok(Model model) {
        model.addAttribute("session", "session");
        model.addAttribute("memberid", "memberid 님 반갑습니다.");
        return "community";
    }

    @RequestMapping("/community_board_content")
    public String community_board_content(Model model) {
        model.addAttribute("session", "session");
        model.addAttribute("memberid", "memberid 님 반갑습니다.");
        return "community_board_content";
    }

    @RequestMapping("/community_board_edit")
    public String community_board_edit(Model model) {
        model.addAttribute("session", "session");
        model.addAttribute("memberid", "memberid 님 반갑습니다.");
        return "community_board_edit";
    }

    @RequestMapping("/community_board_edit_ok")
    public String community_board_edit_ok(Model model) {
        model.addAttribute("session", "session");
        model.addAttribute("memberid", "memberid 님 반갑습니다.");
        return "community";
    }

    @RequestMapping("/community_board_delete")
    public String community_board_delete(Model model) {
        model.addAttribute("session", "session");
        model.addAttribute("memberid", "memberid 님 반갑습니다.");
        return "community_board_delete";
    }
    @RequestMapping("/community_board_delete_ok")
    public String community_board_delete_ok(Model model) {
        model.addAttribute("session", "session");
        model.addAttribute("memberid", "memberid 님 반갑습니다.");
        return "community";
    }
}
