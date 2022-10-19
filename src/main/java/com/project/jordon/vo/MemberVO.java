package com.project.jordon.vo;

import lombok.Data;

import java.sql.Date;

@Data
public class MemberVO {

    private String memberid;
    private String memberpassword;
    private String memberpassword2;
    private String membername;
    private String memberbirth;
    private String membergender;
    private String memberemail;
    private String memberemailauth;
    private String memberaddress1; // 시
    private String memberaddress2; // 구,군
    private String memberaddress3; // 도로명주소
    private String memberaddress4; // 상세주소
    private String memberaddress5; // 참고주소
    private String memberphonenumber;
    private Date memberregdate; // 등록날짜
    private Date updatedate; // 수정날짜
}
