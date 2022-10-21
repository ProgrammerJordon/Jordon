package com.project.jordon.dao;

import com.project.jordon.vo.MemberVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MemberDAOImpl implements MemberDAO {

    @Autowired
    SqlSession sqlSession;

    @Override
    public void insertMember(MemberVO m) {this.sqlSession.insert("member_insert", m);
    }

    @Override
    public MemberVO loginMember(String memberid) {
        return this.sqlSession.selectOne("member_login", memberid);
    }



}
