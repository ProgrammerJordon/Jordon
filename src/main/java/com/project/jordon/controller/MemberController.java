package com.project.jordon.controller;

import com.project.jordon.service.MemberService;
import com.project.jordon.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class MemberController {

    @Autowired
    private MemberService memberserivce;
    private JavaMailSender javaMailSender;

    @RequestMapping("/login")
    public String findo_login() {
        return "findo_login";
    }

    @RequestMapping("/findo_login_ok")
    public String findo_login_ok(String memberid, String memberpassword, HttpServletRequest request) {

        MemberVO m = this.memberserivce.loginMember(memberid);
        if (m == null) {
            return "findo_login";
        } else if (m.getMemberid().equals(memberid) && m.getMemberpassword().equals(memberpassword)) {
            System.out.println("아이디 비밀번호 둘다 같음");
            String membername1 = m.getMembername();
            System.out.println(membername1);
            HttpSession session = request.getSession();
            session.setMaxInactiveInterval(30 * 60);
            session.setAttribute("session", memberid);
            session.setAttribute("memberpassword", m.getMemberpassword());
            session.setAttribute("memberpassword2", m.getMemberpassword2());
            session.setAttribute("membername", m.getMembername());
            session.setAttribute("memberbirth", m.getMemberbirth());
            session.setAttribute("membergender", m.getMembergender());
            session.setAttribute("memberemail", m.getMemberemail());
            session.setAttribute("memberaddress1", m.getMemberaddress1());
            session.setAttribute("memberaddress2", m.getMemberaddress2());
            session.setAttribute("memberaddress3", m.getMemberaddress3());
            session.setAttribute("memberaddress4", m.getMemberaddress4());
            session.setAttribute("memberaddress5", m.getMemberaddress5());
            session.setAttribute("memberphonenumber", m.getMemberphonenumber());
            session.setAttribute("memberregdate", m.getMemberregdate());
            session.setAttribute("memberupdate", m.getMemberupdate());

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
        if (m != null) {
            if (m.getMemberemail().equals(memberemail) && m.getMembername().equals(membername)) {
                System.out.println("찾았다 요놈");
                String memberid = m.getMemberid();
                model.addAttribute("memberid", memberid);
                return "findo_search_id_completement";
            } else {
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
    public String search_memberpassword_completement(String memberid, String memberemail, String membername, Model model) {
        MemberVO m = this.memberserivce.passwordsearchMember(memberid);
        System.out.println(m);
        if (m != null) {
            if (m.getMemberid().equals(memberid) && m.getMemberemail().equals(memberemail) && m.getMembername().equals(membername)) {
                System.out.println("찾았다 요놈");
                String memberpassword = m.getMemberpassword();
                model.addAttribute("memberpassword", memberpassword);
                return "findo_search_password_completement";
            } else {
                System.out.println("찾지 못함");
                return "findo_search_password";
            }
        }
        return "findo_search_password";
    }

    // 회원 profile
    @RequestMapping("/profile")
    public String findo_profile(MemberVO m, HttpSession session, Model model) {
        return "findo_profile";
    }


    // 회원정보 변경 컨트롤러
    @RequestMapping("/profile_update")
    public String profile_update() {
        return "findo_profile_update";
    }

    @RequestMapping("/profile_update_completement")
    public String profile_update_completement(MemberVO m, HttpSession session) {
        int updateinfo = this.memberserivce.updateMember(m);
        System.out.println(updateinfo);
        if (updateinfo == 1) {
            System.out.println("업데이트성공");
            session.invalidate();
            return "findo_profile_update_completement";
        } else {
            System.out.println("업데이트실패");
            return "findo_profile_update";
        }
    }

    // 회원정보 삭제 컨트롤러
    @RequestMapping("/profile_delete")
    public String profile_delete() {
        // 회원탈퇴시 session.invalidate() 해줘야됨
        return "findo_profile_delete";
    }

    @RequestMapping("/profile_delete_completement")
    public String profile_delete_completement(MemberVO m, HttpSession session) {
        int deletemember = this.memberserivce.deleteMember(m);
        System.out.println(deletemember);
        if (deletemember == 1) {
            System.out.println("회원탈퇴 성공");
            session.invalidate();
            return "findo_profile_delete_completement";
        } else {
            System.out.println("회원탈퇴 실패");
            return "findo_profile_delete";
        }
    }

    @RequestMapping(value = "/signup_form", method = {RequestMethod.GET})
    @ResponseBody
    public int idcheck(String memberid) {
        int result = memberserivce.idcheck(memberid);
        return result;
    }

    @RequestMapping(value = "/signup_form", method = {RequestMethod.POST})
    public String findo_signup_form() {
        return "findo_signup_form";
    }


    @RequestMapping("/findo_signup_form_ok")
    public String findo_signup_form_ok(MemberVO m) {
        if (m.getMemberid() != null && m.getMemberpassword() != null && m.getMemberpassword2() != null && m.getMembername() != null && m.getMemberbirth() != null && m.getMembergender() != null && m.getMemberemail() != null && m.getMemberemailauth() != null && m.getMemberaddress1() != null && m.getMemberaddress2() != null && m.getMemberaddress3() != null && m.getMemberaddress4() != null && m.getMemberphonenumber() != null) {
            this.memberserivce.insertMember(m);
            System.out.println("저장됨");
            return "findo_signup_completement";
        } else {
            System.out.println("저장안됨");
            return "findo_signup_form";
        }
    }
}
