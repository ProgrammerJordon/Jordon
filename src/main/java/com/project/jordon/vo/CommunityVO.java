package com.project.jordon.vo;

import lombok.Data;

import java.sql.Date;

@Data
public class CommunityVO {
    private int communitynumber; // 글번호
    private String communitywriter; // 글쓴이
    private String communitytitle; // 글제목
    private String communitycontents; // 글내용
    private int communityhit; // 조회수
    private Date communityregdate; // 등록날짜

}

