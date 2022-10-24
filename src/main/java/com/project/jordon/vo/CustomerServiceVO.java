package com.project.jordon.vo;

import lombok.Data;

import java.util.Date;

@Data
public class CustomerServiceVO {
    private String memberid;
    private String memberemail;
    private String membername;
    private String customerservicetitle;
    private String customerservicecontents;
    private Date customerservicedate;
}
