package com.project.jordon.stock;
import org.json.simple.parser.ParseException;

import java.io.IOException;
// can delete whenever you want here
public class StockRealTime {
    public static void main(String[] args) throws ParseException, IOException {
        StockInfo stockInfo = new StockInfo();
        stockInfo.getStock("005930");
    }
}