package com.project.jordon.service;

import com.project.jordon.vo.MemberVO;

public interface MemberService {
    //     Bring method from DAO
    // 회원가입
    void insertMember(MemberVO m);
    // 로그인
    MemberVO loginMember(String memberid);

    // 중복아이디 확인

}
