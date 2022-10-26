package com.project.jordon.vo;

import lombok.Data;

@Data
public class BoardVO {
    private int bno;
    private String writer;
    private String title;
    private String content;
    private int viewcnt; // 조회수
    private String regdate;
    private int replycnt;

    private int startrow; // 시작번호
    private int endrow; // 끝번호
}
