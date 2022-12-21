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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

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
    public String findo_login_ok(String memberid, String memberpassword, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        MemberVO m = this.memberserivce.loginMember(memberid);
        if (m == null) {
            System.out.println("아이디없음 비밀번호 자체를 비교를 안함");
            out.println("<script>");
            out.println("alert('존재하지 않는 아이디입니다.');");
            out.println("</script>");
            out.flush();
            return "findo_login";
        } else if (m.getMemberid().equals(memberid) && m.getMemberpassword().equals(memberpassword)) {
            System.out.println("아이디 비밀번호 둘다 같음");
            String membername1 = m.getMembername();
            System.out.println(membername1);
            HttpSession session = request.getSession();
            session.setMaxInactiveInterval(30 * 60);
            session.setAttribute("session", memberid);
            session.setAttribute("memberpassword", m.getMemberpassword()); // 비밀번호 중복 확인을 위해 password2 함께 두고 자바스크립트로 비교할 것
            session.setAttribute("memberpassword2", m.getMemberpassword2());
            session.setAttribute("membername", m.getMembername());
            session.setAttribute("memberbirth", m.getMemberbirth());
            session.setAttribute("membergender", m.getMembergender());
            session.setAttribute("memberemail", m.getMemberemail());
            session.setAttribute("memberemailauth", m.getMemberemailauth());
            session.setAttribute("memberaddress1", m.getMemberaddress1());
            session.setAttribute("memberaddress2", m.getMemberaddress2());
            session.setAttribute("memberaddress3", m.getMemberaddress3());
            session.setAttribute("memberaddress4", m.getMemberaddress4());
            session.setAttribute("memberaddress5", m.getMemberaddress5()); // 카카오 API를 사용해서 회원주소 목록을 데이터베이스에 넣기 위해 가져왔으나 열어도 되고 닫아도 되는 세션 값 Memberaddresss5
            session.setAttribute("memberphonenumber", m.getMemberphonenumber());
            session.setAttribute("memberregdate", m.getMemberregdate());
            session.setAttribute("memberupdate", m.getMemberupdate());
        } else {
            System.out.println("비밀번호 틀림");
            out.println("<script>");
            out.println("alert('옳바른 비밀번호를 입력해주세요.');");
            out.println("</script>");
            out.flush();
            return "findo_login";
        }
        return "findo";
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
    public String search_memberid_completement(String memberemail, String membername, Model model, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        MemberVO m = this.memberserivce.idsearchMember(memberemail);
        PrintWriter out = response.getWriter();
        System.out.println(m);
        if (m != null) {
            out.println("<script>");
            out.println("alert('유효하지 않은 아이디입니다.')");
            out.println("</script>");
            out.flush();
            if (m.getMemberemail().equals(memberemail) && m.getMembername().equals(membername)) {
                System.out.println("찾았다 요놈");
                String memberid = m.getMemberid();
                model.addAttribute("memberid", memberid);
                out.println("<script>");
                out.println("alert('유효한 아이디를 찾았습니다.')");
                out.println("</script>");
                out.flush();
                return "findo_search_id_completement";
            } else {
                out.println("<script>");
                out.println("alert('일치하는 회원정보가 없습니다.')");
                out.println("</script>");
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

    // 회원정보 변경완료 클릭시 발생하는 컨트롤러
    @RequestMapping("/profile_update_completement")
    public String profile_update_completement(MemberVO m, HttpSession session, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        if (m.getMemberid() != null && m.getMemberpassword() != null && m.getMemberpassword2().equals(m.getMemberpassword()) == true && m.getMembername() != null && m.getMemberemail() != null && m.getMemberaddress1() != null && m.getMemberaddress2() != null && m.getMemberaddress3() != null && m.getMemberaddress4() != null && m.getMembergender() != null && m.getMemberphonenumber() != null) {
            int updateinfo = this.memberserivce.updateMember(m);
            System.out.println("업데이트일단 저장됨");
            System.out.println(updateinfo);
            if (updateinfo == 1) {
                System.out.println("업데이트성공");
                session.invalidate();
                out.println("<script>");
                out.println("alert('회원정보가 업데이트 되었습니다.')");
                out.println("</script>");
                out.flush();
                return "findo_profile_update_completement";
            } else {
                System.out.println("업데이트실패");
                out.println("<script>");
                out.println("alert('회원정보가 업데이트가 실패하였습니다.')");
                out.println("</script>");
                out.flush();
                return "findo_profile_update";
            }
        }
        return "findo_profile_update";
    }

    // 회원정보 삭제로 이동하는 컨트롤러
    @RequestMapping("/profile_delete")
    public String profile_delete() {return "findo_profile_delete";}

    // 회원탈퇴에 관련한 컨트롤러
    @RequestMapping("/profile_delete_completement")
    public String profile_delete_completement(MemberVO m, HttpSession session, HttpServletResponse response) throws IOException {
        int deletemember = this.memberserivce.deleteMember(m);
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        System.out.println(deletemember);
        if (deletemember == 1) {
            // 삭제 완료된 환경을 보여줘야됨
            out.println("<script>");
            out.println("alert('회원탈퇴가 성공하였습니다.')");
            out.println("</script>");
            System.out.println("회원탈퇴 성공");
            session.invalidate();
            return "findo_profile_delete_completement";
        } else {
            out.println("<script>");
            out.println("alert('회원탈퇴 정보가 일치하지 않습니다.')");
            out.println("</script>");
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
    public String findo_signup_form_ok(MemberVO m, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        if (m.getMemberid() != null && m.getMemberpassword() != null && m.getMemberpassword2() != null && m.getMembername() != null && m.getMemberbirth() != null && m.getMembergender() != null && m.getMemberemail() != null && m.getMemberemailauth() != null && m.getMemberaddress1() != null && m.getMemberaddress2() != null && m.getMemberaddress3() != null && m.getMemberaddress4() != null && m.getMemberphonenumber() != null) {
            this.memberserivce.insertMember(m);
            out.println("<script>");
            out.println("alert('회원가입이 완료되었습니다.')");
            out.println("</script>");
            out.flush();
            System.out.println("저장됨");
            return "findo_signup_completement";
        } else {
            out.println("<script>");
            out.println("alert('옳바른 입력값을 작성하시길 바랍니다.')");
            out.println("</script>");
            out.flush();
            System.out.println("저장안됨");
            return "findo_signup_form";
        }
    }
}
