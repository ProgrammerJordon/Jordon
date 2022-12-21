package com.project.jordon.controller;

import com.project.jordon.service.CustomerServiceService;
import com.project.jordon.vo.CustomerServiceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class CustomerServiceController {

    @Autowired
    private CustomerServiceService customerserviceService;

    @RequestMapping("/customerservice")
    public String customerservice() {
        return "findo_customerservice";
    }

    @RequestMapping("/customerservice_completement")
    public String customerservice_completement(CustomerServiceVO c, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter(); // throws Exception 추가해 줘야됨 입출력 관련 예외처리해서 화면에 자바스크립트 입출력 띄워주기
        if(c.getMemberid() != null &&  c.getMemberemail() != null && c.getMembername() != null && c.getCustomerservicetitle() != null && c.getCustomerservicecontents() != null) {
            this.customerserviceService.insertCustomerservice(c);
            // 저장 성공 유무에 따라 회원들에게 문의의 데이터베이스 저장 완료에 대한 글 띄워주기
            out.println("<script>");
            out.println("alert('문의가 정상적으로 제출되었습니다.')");
            out.println("</script>");
            System.out.println("문의 저장 완료");
            return "findo_customerservice_completement";
        }else {
            out.println("<script>");
            out.println("alert('문의 제목과 문의내용을 옳바르게 작성해주시길 바랍니다.')");
            out.println("</script>");
            System.out.println("문의 저장 실패");
            return "findo_customerservice";
        }
    }
}
