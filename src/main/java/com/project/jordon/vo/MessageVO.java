package com.project.jordon.vo;

import lombok.Data;

@Data
public class MessageVO {
    private int mid;
    private String targetid;
    private String sender;
    private String message;
    private String senddate;
}
