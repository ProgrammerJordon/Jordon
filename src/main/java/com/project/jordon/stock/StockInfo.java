package com.project.jordon.stock;

import com.project.jordon.unixTime.MillToDate;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class StockInfo {
    public JSONObject getStock(String stockCode)throws ParseException, IOException{
        String BaseUrl = "https://invest.zum.com/api/domestic/stock/";
        String code = stockCode;
        URL url = new URL(BaseUrl + code);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        JSONObject result = null;
        StringBuilder sb = new StringBuilder();


        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        conn.setDoOutput(true);


        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

        while (br.ready()){
            sb.append(br.readLine());
        }
        conn.disconnect();



        while(br.ready()) {
            sb.append(br.readLine());
            //System.out.println(sb);
        }
        result = (JSONObject) new JSONParser().parse(sb.toString());
        StringBuilder out = new StringBuilder();


        //  api의 전체 데이터 중 차트에 그려질 기간별 데이터만 parsing
        JSONObject chart= (JSONObject) result.get("chart");
        JSONObject yearly = (JSONObject) chart.get("YEARLY");//1년치 데이터
        JSONObject yearly3 = (JSONObject) chart.get("YEARLY3"); //3년치 데이터
        JSONObject monthly = (JSONObject) chart.get("MONTHLY");// 1달치 데이터
        JSONObject monthly3 = (JSONObject) chart.get("MONTHLY3");//3달치 데이터
        JSONObject daily = (JSONObject) chart.get("DAILY"); //당일 하루 데이터


        //api 전체 데이터 중 투자정보에 관한 데이터만 parsing
        JSONObject outPrice = (JSONObject) result.get("price");
        JSONObject detail = (JSONObject) outPrice.get("detail");
        JSONObject investmentIndicator = (JSONObject) outPrice.get("investmentIndicator") ;



        //기간별 데
        JSONArray yearlyArray = (JSONArray) yearly.get("chart");
        JSONArray yearly3Array = (JSONArray) yearly3.get("chart");
        JSONArray monthlyArray = (JSONArray) monthly.get("chart");
        JSONArray monthly3Array = (JSONArray) monthly3.get("chart");
        JSONArray dailyArray = (JSONArray) daily.get("chart");

        JSONObject tmp;
        MillToDate millToDate = new MillToDate();
        //LinkedHashMap<String, String> stockDatePrice = new LinkedHashMap<>();

        //  return할 json 객체에 일간 월간 연간 날짜와 가격정보를 담는 부분
        JSONObject returnData = new JSONObject(); //리턴할 최종 데이터

        //1년치 데이터
        JSONObject yearlyData = new JSONObject();
        JSONArray yearlyDate = new JSONArray();
        JSONArray yearlyPrice = new JSONArray();
        for(int i=0; i<yearlyArray.size(); i++) {
            tmp = (JSONObject) yearlyArray.get(i);
            String date = (millToDate.miltoDate((long)(tmp.get("time"))));
            String price = (tmp.get("price").toString());
            //stockDatePrice.put(date, price);
            yearlyDate.add(date);
            yearlyPrice.add(price);
        }
        yearlyData.put("date", yearlyDate);
        yearlyData.put("price", yearlyPrice);

        //3년치 데이터
        JSONObject yearly3Data = new JSONObject();
        JSONArray yearly3Date = new JSONArray();
        JSONArray yearly3Price = new JSONArray();
        for(int i=0; i<yearly3Array.size(); i++){
            tmp = (JSONObject) yearly3Array.get(i);
            String date = (millToDate.miltoDate((long)(tmp.get("time"))));
            String price = (tmp.get("price").toString());
            yearly3Date.add(date);
            yearly3Price.add(price);
        }
        yearly3Data.put("date", yearly3Date);
        yearly3Data.put("price", yearly3Price);

        //3개월치 데이터
        JSONObject monthly3Data = new JSONObject();
        JSONArray monthly3Date = new JSONArray();
        JSONArray monthly3Price = new JSONArray();
        for(int i=0; i<monthly3Array.size(); i++){
            tmp = (JSONObject) monthly3Array.get(i);
            String date = (millToDate.miltoDate((long)(tmp.get("time"))));
            String price = (tmp.get("price").toString());
            monthly3Date.add(date);
            monthly3Price.add(price);
        }
        monthly3Data.put("date", monthly3Date);
        monthly3Data.put("price", monthly3Price);

        //1개월치 데이터
        JSONObject monthlyData = new JSONObject();
        JSONArray monthlyDate = new JSONArray();
        JSONArray monthlyPrice = new JSONArray();
        for(int i=0; i<monthlyArray.size(); i++){
            tmp = (JSONObject) monthlyArray.get(i);
            String date = (millToDate.miltoDate((long)(tmp.get("time"))));
            String price = (tmp.get("price").toString());
            //System.out.println(date);
            monthlyDate.add(date);
            monthlyPrice.add(price);
        }
        monthlyData.put("date", monthlyDate);
        monthlyData.put("price", monthlyPrice);
        //System.out.println(monthlyData);

        //1일치 데이터
        JSONObject dailyData = new JSONObject();
        JSONArray dailyDate = new JSONArray();
        JSONArray dailyPrice = new JSONArray();
        for(int i=0; i<dailyArray.size(); i++){
            tmp = (JSONObject) dailyArray.get(i);
            String date = (millToDate.miltoDate((long)(tmp.get("time"))));
            String price = (tmp.get("price").toString());
            dailyDate.add(date);
            dailyPrice.add(price);
        }
        dailyData.put("date", dailyDate);
        dailyData.put("price", dailyPrice);


        //각 기간별 데이터 객체를 하나의 객체로 합치기
        returnData.put("year3", yearly3Data);
        returnData.put("year1", yearlyData);
        returnData.put("month3", monthly3Data);
        returnData.put("month1", monthlyData);
        returnData.put("detail", detail);
        returnData.put("investmentIndicator", investmentIndicator);

        //System.out.println(returnData);
        returnData.put("daily", dailyData);

        //System.out.println(yearlyArray);
        System.out.println(returnData);
        return new JSONObject(returnData);

    }
}

