package com.project.jordon.controller;

import com.project.jordon.service.AnnounceService;
import com.project.jordon.vo.AnnouncementVO;
import com.project.jordon.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller //@Controller애노테이션에 의해서 스프링에 컨트롤러로 인식됨. =>스프링 MVC게시판 컨트롤러 클래스
@RequestMapping("/announcement/*") //컨트롤 자체에 매핑주소
public class AnnouncementController {

    @Autowired //자동의존성 주입
    private AnnounceService announceService;

    //스프링 MVC게시판 글쓰기 폼
    @RequestMapping(value = "/board_write", method = RequestMethod.GET) //GET으로 접근하는 board_write 매핑주소를 처리
    public void board_write(Model model, HttpServletRequest request, MemberVO m) {//리턴타입이 없는 void형이면 매핑주소가 뷰페이지 파일명이 된다.
        HttpSession session = request.getSession();
        session.getAttribute("session");
        int page = 1;
        if (model.getAttribute("page") != null) {
            page = Integer.parseInt("page");
        }
        model.addAttribute("page", page);//page키이름에 쪽번호 저장=>페이징에서 책갈피기능을 구현
    }//board_write()

    //게시물저장
    @RequestMapping(value = "/board_write", method = RequestMethod.POST) //post로 접근하는 매핑주소 처리,폼태그에서 액션 속성을 지정하지
    //않으면 이전 매핑주소가 액션 매핑주소가 된다. 같은 매핑주소 구분은 method인 get or post로 구분한다.
    public String board_write_ok(AnnouncementVO a, RedirectAttributes rttr, HttpSession session, MemberVO m) {
        /* BoardVO b는 board_write.jsp의 네임피라미터 이름과 BoardVO 빈클래스의 변수명이 같으면 b객체에 글쓴이와 제목,글내용이 저장되어 있다.
         * 코드라인을 줄이는효과가 발생한다.
         */
        session.getAttribute("session");
        this.announceService.insertBoard(a);//게시물저장
        return "redirect:/announcement/board_list";
    }//board_write_ok()


    //게시물 목록
    @GetMapping("/board_list") //get으로 접근하는 매핑주소 board_list등록
    public String board_list(Model m, HttpServletRequest request, AnnouncementVO a, HttpSession session, MemberVO mb) {
        String memberid = (String) session.getAttribute("session");
        session.setAttribute("session", memberid);
        int page = 1;//현재 쪽번호
        int limit = 10;//한페이지 보여지는 목록개수
        if (request.getParameter("page") != null) {
            //get으로 전달된 쪽번호가 있는 경우 실행
            page = Integer.parseInt(request.getParameter("page"));
            //쪽번호를 정수 숫자로 바꿔서 저장
        }
        a.setStartrow((page - 1) * 10 + 1);//시작행번호
        a.setEndrow(a.getStartrow() + limit - 1);//끝행번호


        int listCount = this.announceService.getListCount();
        //총게시물수
        List<AnnouncementVO> blist = this.announceService.getBoardList(a);
        //게시물 목록

        //총페이지수
        int maxpage = (int) ((double) listCount / limit + 0.95);
        //현재페이지에 보여질 시작페이지
        int startpage = (((int) ((double) page / 10 + 0.9)) - 1) * 10 + 1;
        //현재 페이지에 보여질 마지막 페이지
        int endpage = maxpage;

        if (endpage > startpage + 10 - 1) endpage = startpage + 10 - 1;
        //마지막 페이지> 시작페이지+10-1   마지막페이지=시작페이지+10-1

        m.addAttribute("list", blist);//list 키이름에
        //blist 를 저장
        m.addAttribute("totalCount", listCount);
        //totalCount 키이름에 listCount저장
        m.addAttribute("startpage", startpage);
        m.addAttribute("endpage", endpage);
        m.addAttribute("maxpage", maxpage);
        m.addAttribute("page", page);//page키이름에 쪽번호
        m.addAttribute("session", session);

        m.addAttribute("session", memberid);

        //를 저장

        return "announcement/board_list"; //뷰리졸브 경로(뷰페이지 경로) => /WEB-INF/views/board/board_list.jsp
    }//board_list()


    //내용보기+조회수 증가
    @RequestMapping("board_cont") //get or post 방식일때 모두 실행
    public ModelAndView board_cont(@RequestParam("abno") int abno, @RequestParam("page") int page, HttpSession session,MemberVO m) {
        String memberid = (String) session.getAttribute("session");
        session.setAttribute("session", memberid);

        AnnouncementVO b = this.announceService.getBoardCont(abno);//조회수증가와 내용보기
        String cont = b.getContent().replace("\n", "<br/>");//textarea영역에서 엔터키 친부분을 줄바꿈 즉 개행한다.
        ModelAndView cm = new ModelAndView();
        cm.addObject("b", b);//b키이름에 b객체를 저장
        cm.addObject("cont", cont);
        cm.addObject("page", page);//책갈피 기능 구현을 위한 쪽번호 저장
        cm.setViewName("announcement/board_cont2");//뷰페이지 경로
        return cm;
    }//board_cont()

    //게시물 수정폼
    @RequestMapping("/announcement/board_edit")
    public String board_edit(int abno, int page,
                             HttpServletRequest request, MemberVO m, HttpSession session) {
        //int bno,int page로 지정하면 get으로 전달된 bno,page피라미터값을 가져옴
        AnnouncementVO eb = announceService.getBoardCont2(abno);//조회수를 증가하지 않고 번호에 해당하는 레코드를 가져오기
        request.setAttribute("eb", eb);
        request.setAttribute("page", page);
        String memberid = (String) session.getAttribute("session");
        session.setAttribute("session", memberid);
        return "announcement/board_edit";
    }

    //수정완료
    @RequestMapping("board_edit_ok") //get or post 양쪽방식으로 접근하는 board_edit_ok 매핑주소 처리
    public ModelAndView board_edit_ok(int abno,int page,AnnouncementVO eb,HttpSession session) {

        String memberid = (String) session.getAttribute("session");
        session.setAttribute("session", memberid);
        eb.setAbno(abno);//번호값을 저장
        this.announceService.updateBoard(eb);//번호를 기준으로 제목,글쓴이,글내용을 수정

        ModelAndView em=new ModelAndView();
        em.setViewName("redirect:/announcement/board_cont");//매핑주소 경로 설정
        em.addObject("abno",abno);
        em.addObject("page",page);
        return em;
    }//board_edit_ok()


    //게시물 삭제
    @GetMapping("board_del") //board_del 매핑주소 등록, get으로 접근하는 매핑주소를 처리
    public ModelAndView board_del(int abno, int page,RedirectAttributes rttr, HttpSession session, MemberVO m) {
        String memberid = (String) session.getAttribute("session");
        session.setAttribute("session", memberid);
        announceService.deleteBoard(abno);//게시물 삭제
        ModelAndView dm=new ModelAndView();
        dm.setViewName("/announcement/board_list");
        return dm;
    }//board_del()
}



