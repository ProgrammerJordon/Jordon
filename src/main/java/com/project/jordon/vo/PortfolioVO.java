package com.project.jordon.vo;

import lombok.Data;

@Data
public class PortfolioVO {
    //가지고 있는 포폴에 대한 정보
    private String portfolioid;
    private String memberid;
    private String portfolionumber;
    private String portfolioname;
    private String portfolioquantity;
    private String avgprice;
    private String transactiondate;
    private String transactionprice;

    //새로 추가하려고 하는 포폴정보
    private String stock_number;
    private String stock_name;
    private String date;
    private int quantity;
    private int price;

}
