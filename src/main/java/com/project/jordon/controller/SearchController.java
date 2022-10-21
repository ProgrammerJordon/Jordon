package com.project.jordon.controller;

import com.project.jordon.vo.SearchVO;
import com.project.jordon.service.SearchService;
import com.project.jordon.stock.StockInfo;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
public class SearchController {
    @Autowired
    SearchService searchService;

    @RequestMapping("/search_list")
    String index_search_ok(Model listM, HttpSession session, HttpServletResponse response, HttpServletRequest request, SearchVO svo) {

        String find_name = request.getParameter("find_name");
        svo.setFind_name("%"+find_name+"%");

        System.out.println(find_name);
        List<SearchVO> slist = this.searchService.getSearchList(svo);
        listM.addAttribute("slist", slist);
        listM.addAttribute("find_name", find_name);
        return "search_list";
    }
    @RequestMapping("stock_cont")
    public ModelAndView stock_cont(@RequestParam("stocknumber") String stocknumber, String state, SearchVO svo) throws ParseException, IOException {
        //@RequestParam("stocknumber")를 서블릿으로 표현하면 request.getParameter("stocknumber")와 같음.

        StockInfo stockInfo = new StockInfo();
        JSONObject stock = stockInfo.getStock(stocknumber);
        ModelAndView cm = new ModelAndView();
        cm.addObject("stock", stock);

        cm.setViewName("stock_cont");
        return cm;
    }

}