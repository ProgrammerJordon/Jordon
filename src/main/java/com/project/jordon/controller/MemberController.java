package com.project.jordon.controller;

import com.project.jordon.service.MemberService;
import com.project.jordon.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Member;
import java.util.List;

@Controller
public class MemberController {

    @Autowired
    private MemberService memberserivce;

    @RequestMapping("/signup_form")
    public String findo_signup_form(Model model) {
        return "findo_signup_form";
    }



    @RequestMapping("/findo_signup_form_ok")
    public String findo_signup_form_ok(MemberVO m) {
        if(m.getMemberid() != null && m.getMemberpassword() != null && m.getMemberpassword2() != null && m.getMembername() !=null && m.getMemberbirth() != null && m.getMembergender() != null && m.getMemberemail() != null && m.getMemberemailauth() != null && m.getMemberaddress1() != null && m.getMemberaddress2() != null && m.getMemberaddress3() != null && m.getMemberaddress4() != null && m.getMemberphonenumber() != null) {
            this.memberserivce.insertMember(m);
            System.out.println("저장됨");
            return "findo_signup_completement";
        }else {
            System.out.println("저장안됨");
            return "findo_signup_form";
        }
    }

    @RequestMapping("/login")
    public String findo_login() {

        return "findo_login";
    }
    @RequestMapping("/findo_login_ok")
    public String findo_login_ok(String memberid, String memberpassword, HttpServletRequest request, HttpServletResponse response) throws IOException {
        MemberVO m = this.memberserivce.loginMember(memberid);
        PrintWriter out = response.getWriter();
        System.out.println(m);
        if (m != null) {
            if (m.getMemberid().equals(memberid) && m.getMemberpassword().equals(memberpassword)) {
                System.out.println("아이디 비밀번호 둘다 같음");
                HttpSession session = request.getSession();
                session.setAttribute("session", memberid);
                session.getAttribute("session");
                if (m.getMemberid().equals(memberid) == false || m.getMemberpassword().equals(memberpassword) == false) {
                    System.out.println("쓰레기 값이 들어옴");
                    return "redirect:/login";
                } else {
                    System.out.println("정상적인 데이터가 들어옴");
                }
                return "redirect:/findo";
            } else {
                System.out.println("아이디 비밀번호 틀림");
                return "redirect:/login";
            }
        }
        return "redirect:/login";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/findo";
    }

    // 아이디찾기 컨트롤러
    @RequestMapping("/search_memberid")
    public String search_memberid() {
        return "findo_search_id";
    }
    // 아이디 찾기 확인 눌렀을때 컨트롤러
    @RequestMapping("/search_memberid_completement")
    public String search_memberid_completement(String memberemail, String membername, Model model) {
        MemberVO m = this.memberserivce.idsearchMember(memberemail);

        System.out.println(m);
        if(m != null) {
            if(m.getMemberemail().equals(memberemail) && m.getMembername().equals(membername)) {
                System.out.println("찾았다 요놈");
                String memberid = m.getMemberid();
                model.addAttribute("memberid", memberid);
                return "findo_search_id_completement";
            }else {
                System.out.println("찾지 못함");
                return "findo_search_id";
            }
        }
        return "findo_search_id";
    }

    // 비밀번호찾기 컨트롤러
    @RequestMapping("/search_memberpassword")
    public String search_memberpassword() {
        return "findo_search_password";
    }
    // 비밀번호 찾기 확인 눌렀을때 컨트롤러
    @RequestMapping("/search_memberpassword_completement")
    public String search_memberpassword_completement(String memberid,String memberemail, String membername, Model model) {
        MemberVO m = this.memberserivce.passwordsearchMember(memberid);
        System.out.println(m);
        if(m != null) {
            if(m.getMemberid().equals(memberid) && m.getMemberemail().equals(memberemail) && m.getMembername().equals(membername)) {
                System.out.println("찾았다 요놈");
                String memberpassword = m.getMemberpassword();
                model.addAttribute("memberpassword", memberpassword);
                return "findo_search_password_completement";
            }else {
                System.out.println("찾지 못함");
                return "findo_search_password";
            }
        }
        return "findo_search_password";
    }


}
