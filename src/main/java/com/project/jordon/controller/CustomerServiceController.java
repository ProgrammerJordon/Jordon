package com.project.jordon.controller;

import com.project.jordon.service.CustomerServiceService;
import com.project.jordon.vo.CustomerServiceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomerServiceController {

    @Autowired
    private CustomerServiceService customerserviceService;

    @RequestMapping("/customerservice")
    public String customerservice() {
        return "findo_customerservice";
    }

    @RequestMapping("/customerservice_completement")
    public String customerservice_completement(CustomerServiceVO c) {
        if(c.getMemberid() != null &&  c.getMemberemail() != null && c.getMembername() != null && c.getCustomerservicetitle() != null && c.getCustomerservicecontents() != null) {
            this.customerserviceService.insertCustomerservice(c);
            System.out.println("문의 저장 완료");
            return "findo_customerservice_completement";
        }else {
            System.out.println("문의 저장 실패");
            return "findo_customerservice";
        }
    }
}
