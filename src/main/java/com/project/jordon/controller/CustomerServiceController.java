package com.project.jordon.controller;

import com.project.jordon.service.CustomerServiceService;
import com.project.jordon.service.CustomerServiceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CustomerServiceController {

    @Autowired
    private CustomerServiceService customerserviceService;


}
