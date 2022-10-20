package com.project.jordon.service;

import com.project.jordon.dao.MemberDAO;
import com.project.jordon.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService{
    //     Autowired from DAO
    @Autowired // 자동 의존성 주입
    private MemberDAO memberDAO;

    // 회원가입 정보 저장
    @Override
    public void insertMember(MemberVO m) {
        this.memberDAO.insertMember(m);
    }

    @Override
    public MemberVO loginMember(String memberid) {
        return this.memberDAO.loginMember(memberid);
    }

}
