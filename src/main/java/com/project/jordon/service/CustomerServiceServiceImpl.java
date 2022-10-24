package com.project.jordon.service;

import com.project.jordon.dao.CustomerServiceDAO;
import com.project.jordon.vo.CustomerServiceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceServiceImpl implements CustomerServiceService {

    @Autowired
    private CustomerServiceDAO customerserviceDAO;

    @Override
    public void insertCustomerservice(CustomerServiceVO c) {
        this.customerserviceDAO.insertCustomerservice(c);
    }
}
