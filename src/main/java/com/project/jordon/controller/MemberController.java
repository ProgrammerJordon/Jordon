package com.project.jordon.controller;

import com.project.jordon.service.MemberService;
import com.project.jordon.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class MemberController {

    @Autowired
    private MemberService memberserivce;

    @RequestMapping("/signup_form")
    public String findo_signup_form(Model model) {
        model.addAttribute("session", "session");
        model.addAttribute("memberid", "memberid 님 반갑습니다.");
        return "findo_signup_form";
    }

    @RequestMapping("/findo_signup_form_ok")
    public String findo_signup_form_ok(MemberVO m) {
        this.memberserivce.insertMember(m);
        return "redirect:/findo_signup_completement";
    }

    @RequestMapping("/login")
    public String findo_login() {
        return "findo_login";
    }

    @RequestMapping("/findo_login_ok")
    public String findo_login_ok(String memberid, String memberpassword, HttpServletRequest request) {
        MemberVO m = this.memberserivce.loginMember(memberid);
        System.out.println(m);
        if(m.getMemberid().equals(memberid) && m.getMemberpassword().equals(memberpassword)) {
            System.out.println("아이디 비밀번호 둘다 같음");
            HttpSession session = request.getSession();
            session.setAttribute("session", memberid);
                if(session != null) {
                    System.out.println("세션이 존재함");
                }else{
                    System.out.println("세션이 존재하지 않음");
                }
            return "findo";
        }else {
            System.out.println("아이디 비밀번호 틀림");
            return "findo_login";
        }
    }
}
