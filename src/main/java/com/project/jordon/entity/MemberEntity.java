package com.project.jordon.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

// Making table and defining columns
@Data
@Entity
@Table(name="member")
public class MemberEntity {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private String memberid;
    private String memberpassword;
    private String membername;
    private String membergender;
    private String memberbirth;
    private String memberaddress1; // 시
    private String memberaddress2; // 구,군
    private String memberaddress3; // 도로명주소
    private String memberaddress4; // 상세주소
    private String memberemail;
    private String phonenumber;
    // @CreationTimestamp, @UpdateTimestamp는 하이버네이트의 특별한 기능으로 엔티티빈 생성 수정,시점, 등록시점을 날짜값으로 기록
    @CreationTimestamp
    private Timestamp memberregdate; // 등록날짜
    @UpdateTimestamp
    private Timestamp updatedate; // 수정날짜
}
