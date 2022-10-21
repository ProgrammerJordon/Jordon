package com.project.jordon.unixTime;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MillToDate {
    public String miltoDate(Long time){
        Long unixTimeStamp = time;
        long timestamp = (unixTimeStamp)*1000; //자바는 유닉스타임을 초단위가 아닌 천분의 1초 단위로 다뤄서 *1000해줘야 함
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:MM:ss");
        Date date = new Date();
        date.setTime(timestamp);
        String Datetime = sdf.format(date);
        return  Datetime;
    }
}
