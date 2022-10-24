package com.project.jordon.dao;

import com.project.jordon.vo.CustomerServiceVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerServiceDAOImpl implements CustomerServiceDAO {

    @Autowired
    SqlSession sqlSession;

    @Override
    public void insertCustomerservice(CustomerServiceVO c) {
        this.sqlSession.insert("customerservice_insert", c);
    }
}
