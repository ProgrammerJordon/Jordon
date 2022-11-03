package com.project.jordon.service;

import com.project.jordon.dao.MemberDAO;
import com.project.jordon.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public MemberVO idsearchMember(String memberemail) {
        return this.memberDAO.idsearchMember(memberemail);
    }

    @Override
    public MemberVO passwordsearchMember(String memberid) {
        return this.memberDAO.passwordsearchMember(memberid);
    }

    @Override
    public int updateMember(MemberVO m) {
        return this.memberDAO.updateMember(m);
    }

    @Override
    public int deleteMember(MemberVO m) {
        return this.memberDAO.deleteMember(m);
    }

    @Override
    public int idcheck(String memberid) {
        return this.memberDAO.idcheck(memberid);
    }

}
