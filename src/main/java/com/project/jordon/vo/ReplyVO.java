package com.project.jordon.vo;

import lombok.Data;

@Data
public class ReplyVO {
    private int rno;
    private int bno; // 외래키 지정
    private String replyer;
    private String replytext;
    private String regdate;
    private String updatedate;
}
