package com.project.jordon.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@ToString
@Entity // JPA를 다루는 엔티티빈 클래스
@Table(name="test_boards") // tbl_boards 테이블을 생성
public class BoardVO {
    @Id // 기본키 컬럼
    @GeneratedValue(strategy = GenerationType.AUTO) // 특정키에 맞게 식별키를 자동생생
    private Long bno; // 번호
    private String writer; // 글쓴이
    private String Title; // 글제목
    private String content; // 글내용
    @CreationTimestamp
    private Timestamp regdate; // 등록날짜
    @UpdateTimestamp // @CreationTimestamp, @UpdateTimestamp는 하이버네이트의 특별한 기능으로 엔티티빈 생성 수정,시점, 등록시점을 날짜값으로 기록
    private Timestamp updatedate; // 수정날짜

}

