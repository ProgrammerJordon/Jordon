package com.project.jordon.service;

import com.project.jordon.vo.MemberVO;

import java.util.List;

public interface MemberService {
    //     Bring method from DAO
    // 회원가입
    void insertMember(MemberVO m);
    // 로그인
    MemberVO loginMember(String memberid);

    // 중복아이디 확인

    // 아이디 찾기
    MemberVO idsearchMember(String memberemail);
    // 비밀번호 찾기
    MemberVO passwordsearchMember(String memberid);

    // 회원 정보 수정
    int updateMember(MemberVO m);

    // 회원 탈퇴
    int deleteMember(MemberVO m);

    int idcheck(String memberid);

}
