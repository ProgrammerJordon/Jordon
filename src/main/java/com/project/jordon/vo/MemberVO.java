package com.project.jordon.vo;

import lombok.Data;

@Data
public class MemberVO {
    private int MemberNumber;
    private String MemberId;
    private String MemberPassword;
    private String MemberName;
    private String MemberGender;
    private String MemberBirth;
    private String MemberAddress1; // 시
    private String MemberAddress2; // 구,군
    private String MemberAddress3; // 도로명주소
    private String MemberAddress4; // 상세주소
    private String MemberEmail;
    private String PhoneNumber;
}
