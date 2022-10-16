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

    @RequestMapping("/company")
    public String findo_company(Model model) {
        model.addAttribute("session", "session");
        model.addAttribute("memberid", "memberid 님 반갑습니다.");
        return "findo_company";
    }

    @RequestMapping("/investment")
    public String findo_investment(Model model) {
        model.addAttribute("session", "session");
        model.addAttribute("memberid", "memberid 님 반갑습니다.");
        return "findo_investment";
    }

    @RequestMapping("/products")
    public String findo_products(Model model) {
        model.addAttribute("session", "session");
        model.addAttribute("memberid", "memberid 님 반갑습니다.");
        return "findo_products";
    }

    @RequestMapping("/FAQ")
    public String findo_faq(Model model) {
        model.addAttribute("session", "session");
        model.addAttribute("memberid", "memberid 님 반갑습니다.");
        return "findo_faq";
    }

    @RequestMapping("/management")
    public String findo_management(Model model) {
        model.addAttribute("session", "session");
        model.addAttribute("memberid", "memberid 님 반갑습니다.");
        return "findo_management";
    }

    @RequestMapping("/using_law")
    public String findo_using_law(Model model) {
        model.addAttribute("session", "session");
        model.addAttribute("memberid", "memberid 님 반갑습니다.");
        return "findo_using_law";
    }

    @RequestMapping("/financial_transaction_law")
    public String findo_financial_transation_law(Model model) {
        model.addAttribute("session", "session");
        model.addAttribute("memberid", "memberid 님 반갑습니다.");
        return "findo_financial_transaction_law";
    }

    @RequestMapping("/personal_info_law")
    public String findo_personal_info_law(Model model) {
        model.addAttribute("session", "session");
        model.addAttribute("memberid", "memberid 님 반갑습니다.");
        return "findo_personal_info_law";
    }

    @RequestMapping("/management_announcement")
    public String findo_management_announcement(Model model) {
        model.addAttribute("session", "session");
        model.addAttribute("memberid", "memberid 님 반갑습니다.");
        return "findo_management_announcement";
    }

    @RequestMapping("/id_search")
    public String findo_id_search(Model model) {
        model.addAttribute("session", "session");
        model.addAttribute("memberid", "memberid 님 반갑습니다.");
        return "findo_id_search";
    }

    @RequestMapping("/password_search")
    public String findo_password_search(Model model) {
        model.addAttribute("session", "session");
        model.addAttribute("memberid", "memberid 님 반갑습니다.");
        return "findo_password_search";
    }

    @RequestMapping("/signup_agreement")
    public String findo_signup_agreement(Model model) {
        model.addAttribute("session", "session");
        model.addAttribute("memberid", "memberid 님 반갑습니다.");
        return "findo_signup_agreement";
    }

    @RequestMapping("/signup_form")
    public String findo_signup_form(Model model) {
        model.addAttribute("session", "session");
        model.addAttribute("memberid", "memberid 님 반갑습니다.");
        return "findo_signup_form";
    }

    @RequestMapping("/login_ok")
    public String findo_login_ok(Model model) {
        model.addAttribute("session", "session");
        model.addAttribute("memberid", "memberid 님 반갑습니다.");
        return "findo";
    }

    @RequestMapping("/profile")
    public String findo_profile(Model model) {
        model.addAttribute("session", "session");
        model.addAttribute("memberid", "memberid 님 반갑습니다.");
        return "findo_profile";
    }

    @RequestMapping("/event")
    public String findo_event(Model model) {
        model.addAttribute("session", "session");
        model.addAttribute("memberid", "memberid 님 반갑습니다.");
        return "findo_event";
    }

    @RequestMapping("/gamezone")
    public String findo_gamezone(Model model) {
        model.addAttribute("session", "session");
        model.addAttribute("memberid", "memberid 님 반갑습니다.");
        return "findo_gamezone";
    }

    @RequestMapping("/customerservice")
    public String findo_customerservice(Model model) {
        model.addAttribute("session", "session");
        model.addAttribute("memberid", "memberid 님 반갑습니다.");
        return "findo_customerservice";
    }

}
