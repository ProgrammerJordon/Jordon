package com.project.jordon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
public class MainController {

    @RequestMapping("/findo")
    public String index() {
        return "findo";
    }

    @RequestMapping("/portfolio")
    public String findo_portfolio() {
        return "findo_portfolio";
    }

    @RequestMapping("/news")
    public String findo_news() {
        return "findo_news";
    }

    @RequestMapping("/search")
    public String findo_search() {
        return "findo_search";
    }

    @RequestMapping("/announcement")
    public String findo_announcement() {
        return "findo_announcement";
    }

    @RequestMapping("/company")
    public String findo_company() {
        return "findo_company";
    }

    @RequestMapping("/investment")
    public String findo_investment() {
        return "findo_investment";
    }

    @RequestMapping("/products")
    public String findo_products() {
        return "findo_products";
    }

    @RequestMapping("/FAQ")
    public String findo_faq() {
        return "findo_faq";
    }

    @RequestMapping("/management")
    public String findo_management() {
        return "findo_management";
    }

    @RequestMapping("/using_law")
    public String findo_using_law() {
        return "findo_using_law";
    }

    @RequestMapping("/financial_transaction_law")
    public String findo_financial_transaction_law() {
        return "findo_financial_transaction_law";
    }

    @RequestMapping("/personal_info_law")
    public String findo_personal_info_law() {
        return "findo_personal_info_law";
    }

    @RequestMapping("/management_announcement")
    public String findo_management_announcement() {
        return "findo_management_announcement";
    }

    @RequestMapping("/signup_agreement")
    public String findo_signup_agreement() {
        return "findo_signup_agreement";
    }

    @RequestMapping("/findo_signup_completement")
    public String findo_signup_completement() {
        return "findo_signup_completement";
    }

    @RequestMapping("/event")
    public String findo_event() {
        return "findo_event";
    }

    @RequestMapping("/gamezone")
    public String findo_gamezone() {
        return "findo_gamezone";
    }

}
