package com.project.jordon.controller;

import com.project.jordon.service.PortfolioService;
import com.project.jordon.stock.StockInfo;
import com.project.jordon.vo.PortfolioVO;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


@Controller
public class MainController {

    @Autowired
    PortfolioService portfolioService;

    @RequestMapping("/findo")
    public String index(Model listM, PortfolioVO pvo, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{

        response.setContentType("text/html;Charset=UTF-8");
        String id = (String) session.getAttribute("session");
        PrintWriter out = response.getWriter();
        if (id == null) {
            return "findo";
        } else {
//            System.out.println("login은 가능함.");
            pvo.setMemberid(id);
            //db에서 불러온 보유 포트폴리오 리스트
            List<PortfolioVO> plist = this.portfolioService.getPortfolioList(pvo);
            //html로 내보낼 json 객체를 담을 리스트.
            List<JSONObject> klist = new ArrayList<>();
            for (int i = 0; i < plist.size(); i++) {
                String num = plist.get(i).getPortfolionumber();//종목코드
                int quantity = Integer.parseInt(plist.get(i).getPortfolioquantity());//보유수량
                int tprice = Integer.parseInt(plist.get(i).getTransactionprice());//매수금액
                Double avgprice = Double.parseDouble(plist.get(i).getAvgprice());//평균단가

                //getStock()메서드를 이용해서 detail key값을 가져와서 지정함.
                JSONObject object = StockInfo.getStock(num);
                JSONObject objectdetail = (JSONObject) object.get("detail");

                //1000단위마다 ','찍어주는 함수 Decimal Format
                DecimalFormat df = new DecimalFormat("###,###,###,###,###");

                //현재 주식가격
                Long cprice = (Long) objectdetail.get("currentPrice");

                //수익, 수익률 계산하는 부분

                //손익
                Double profitAndLoss;
                profitAndLoss = Double.valueOf((cprice - avgprice)) * quantity;

                //수익률 = (현재주식가격 - 구매가격)/구매가격*100
                double earningsrate;
                earningsrate = (double) (cprice - avgprice) / tprice * 100;
                double eprice = Math.round(earningsrate * 100) / 100.0;


                //JSON에 db에서 불러온 모유수량과 매수금액, 손익, 수익률,평균단가 저장. df.format() 이용해서 1000단위마다 ',' 찍어서 가격, 수량을 json에저장함.

                Long curr = (long) objectdetail.get("currentPrice");//json에서 현재가를 가져와서
                objectdetail.put("currentPrice", "₩" + df.format(curr));//현재가에 ','를 붙인폼으로 수정하여 json에 넣어줌.
                objectdetail.put("quantity", df.format(quantity));//json에 현재수량을 넣어줌
                objectdetail.put("tprice", df.format(tprice));//json에 거래가격을 넣어줌.

                //손익을 json에 넣어줄건데 양수면 '+'와 '₩' 붙여서, 음수면 '-'와 '₩'붙여서 json에 넣어줌
                if (profitAndLoss > 0) {
                    objectdetail.put("profitAndLoss", "+" + "₩" + df.format(profitAndLoss));//
                } else {
                    objectdetail.put("profitAndLoss", "-" + "₩" + df.format(Math.abs(profitAndLoss)));
                }

                //eprice : 수익률임. 수익률이 양수면 '+'와 '%'를 붙여서, 음수면 '-'와 '%'를 붙여서 json에 넣어줌.
                if (eprice > 0) {
                    objectdetail.put("eprice", "+" + df.format(eprice) + "%");
                } else {
                    objectdetail.put("eprice", df.format(eprice) + "%");
                }
                //평균가격에
                objectdetail.put("avgprice", "₩" + df.format(avgprice));
                klist.add(objectdetail);
            }
            listM.addAttribute("plist", plist);
            listM.addAttribute("klist", klist);
            System.out.println("klist : " + klist);
//            //System.out.println("plist : "+plist.get(1).getPortfolionumber());
//            listM.addAttribute("plist",plist);
//            List<String> numlist;
//            String portNum = (String) plist.get(i).
        return "findo";
        }
    }

    @RequestMapping("/search")
    public String findo_search() {
        return "findo_search";
    }

    @RequestMapping("/announcement")
    public String findo_announcement() {
        return "findo_announcement";
    }

    @RequestMapping("/company")
    public String findo_company() {
        return "findo_company";
    }

    @RequestMapping("/investment")
    public String findo_investment() {
        return "findo_investment";
    }

    @RequestMapping("/products")
    public String findo_products() {
        return "findo_products";
    }


    @RequestMapping("/management")
    public String findo_management() {
        return "findo_management";
    }

    @RequestMapping("/using_law")
    public String findo_using_law() {
        return "findo_using_law";
    }

    @RequestMapping("/financial_transaction_law")
    public String findo_financial_transaction_law() {
        return "findo_financial_transaction_law";
    }

    @RequestMapping("/personal_info_law")
    public String findo_personal_info_law() {
        return "findo_personal_info_law";
    }

    @RequestMapping("/signup_agreement")
    public String findo_signup_agreement() {
        return "findo_signup_agreement";
    }

    @RequestMapping("/findo_signup_completement")
    public String findo_signup_completement() {
        return "findo_signup_completement";
    }

    @RequestMapping("/event")
    public String findo_event() {
        return "findo_event";
    }

    @RequestMapping("/gamezone")
    public String findo_gamezone() {
        return "findo_gamezone";
    }

//    @RequestMapping("/chat")
//    public ModelAndView chat() {
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("chat");
//        return mv;
//    }

    @RequestMapping("/community")
    public ModelAndView community() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("findo_community");
        return mv;
    }


}
