package com.project.jordon.controller;

import com.project.jordon.service.PortfolioService;
import com.project.jordon.stock.StockInfo;
import com.project.jordon.vo.PortfolioVO;
import com.project.jordon.vo.SearchVO;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Controller
public class PortfolioController {

    @Autowired
    PortfolioService portfolioService;

    @RequestMapping("/portfolio")
    String findo_portfolio(Model listM, PortfolioVO pvo, HttpServletRequest request, HttpServletResponse response, HttpSession session)throws Exception {
        response.setContentType("text/html;Charset=UTF-8");
        String id = (String) session.getAttribute("session");
        PrintWriter out = response.getWriter();

//            System.out.println("login은 가능함.");
        pvo.setMemberid(id);
        List<PortfolioVO> plist = this.portfolioService.getPortfolioList(pvo);//db에서 불러온 보유 포트폴리오 리스트
        List<JSONObject> klist = new ArrayList<>(); //html로 내보낼 json 객체를 담을 리스트.
        for(int i=0; i<plist.size(); i++){
            //
            String num = plist.get(i).getPortfolionumber();//종목코드
            String quantity = plist.get(i).getPortfolioquantity();//보유수량
            String tprice = plist.get(i).getTransactionprice();//매수금액
            String avgprice = plist.get(i).getAvgprice();//평균단가

            //getStock()메서드를 이용해서 detail key값을 가져와서 지정함.
            JSONObject object = StockInfo.getStock(num);
            JSONObject objectdetail = (JSONObject) object.get("detail");

            Long cprice =  (Long)objectdetail.get("currentPrice");//현재 주식가격

            //수익, 수익률 계산하는 부분

            //손익
            Long profitAndLoss;
            profitAndLoss = Long.valueOf((cprice- Integer.parseInt(avgprice))*Integer.parseInt(quantity));
            System.out.println(profitAndLoss);


            //수익률 = (현재주식가격 - 구매가격)/구매가격*100
            double earningsrate;
            earningsrate = (double) (cprice - Integer.parseInt(avgprice))/Integer.parseInt(tprice)*100;
            double eprice = Math.round(earningsrate*100)/100.0;


            //JSON에 db에서 불러온 모유수량과 매수금액, 손익, 수익률,평균단가 저장
            objectdetail.put("quantity", quantity);
            objectdetail.put("tprice", tprice);
            objectdetail.put("profitAndLoss", profitAndLoss);
            objectdetail.put("eprice",eprice+"%");
            objectdetail.put("avgprice",avgprice);
            klist.add(objectdetail);
        }
        listM.addAttribute("plist", plist);
        listM.addAttribute("klist", klist);
        System.out.println("klist : "+klist);
//            //System.out.println("plist : "+plist.get(1).getPortfolionumber());
//            listM.addAttribute("plist",plist);
//            List<String> numlist;
//            String portNum = (String) plist.get(i).


        return "findo_portfolio";
    }

    @GetMapping("port_cont")
    public ModelAndView getCont(@RequestParam("portfolionumber") String portfolionumber, HttpServletResponse response) throws ParseException, IOException {
        response.setContentType("text/html;Charset=UTF-8");
        StockInfo stockInfo = new StockInfo();
        JSONObject stock = stockInfo.getStock(portfolionumber);
        ModelAndView cm = new ModelAndView();
        cm.addObject("stock", stock);
        System.out.println(stock.get("detail"));

        cm.setViewName("port_cont");

        return cm;
    }
    @RequestMapping("/port_search")
    @ResponseBody
    private List<SearchVO>port_search(HttpServletRequest request, SearchVO svo) {

        String find_name = request.getParameter("find_name");
        svo.setFind_name("%"+find_name+"%");

        System.out.println(find_name);
        List<SearchVO> slist = this.portfolioService.getSearchList(svo);
        System.out.println("slist : "+slist);
        return slist;

    }
    @RequestMapping("port_add")
    public String addPortfolio(PortfolioVO pvo, HttpSession session){
        String userID = (String) session.getAttribute("session");
        pvo.setMemberid(userID);
        PortfolioVO isStockExist = this.portfolioService.checkPortfolio(pvo);
        if(isStockExist == null){
            this.portfolioService.addPortfolio(pvo);
        }else{
            //이미 내 포트폴리오에 해당 종목이 있는경우 평단가를 수정해야 함.
            //수정된 평단가와 수량을 db에 저장함.
            int price = pvo.getPrice();//입력받은 구매가격
            int quantity = pvo.getQuantity();//입력받은 구매수량

            int avgPFromDb = Integer.parseInt(isStockExist.getAvgprice());//db에 저장된 평균단가.
            int quantityFromDb = Integer.parseInt(isStockExist.getPortfolioquantity());//db에 저장된 보유수

            int editQ = quantity+quantityFromDb;//수정된 수량
            double editAVG = (double)((price*quantity)+(avgPFromDb*quantityFromDb))/editQ;

            pvo.setQuantity(editQ);
            pvo.setAvgprice(String.valueOf(editAVG));

            this.portfolioService.editAvgPrice(pvo);

        }
        return "redirect:http://localhost:7777/portfolio";
    }
}