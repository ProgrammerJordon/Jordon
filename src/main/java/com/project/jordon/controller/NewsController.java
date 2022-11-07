package com.project.jordon.controller;

import com.project.jordon.service.NewsService;
import com.project.jordon.vo.MemberVO;
import com.project.jordon.vo.NewsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Controller


@RequestMapping("/news/*")
public class NewsController {
    @Autowired
    NewsService newsService;

    @RequestMapping("/news")
    public String findo_news(Model m, HttpServletRequest request, NewsVO nvo, HttpSession session, MemberVO mvo) {
        int page=1;//현재 쪽번호
        int limit=10;//한페이지 보여지는 목록개수
        if(request.getParameter("page") != null) {
            //get으로 전달된 쪽번호가 있는 경우 실행
            page=Integer.parseInt(request.getParameter("page"));
            //쪽번호를 정수 숫자로 바꿔서 저장
        }
        nvo.setStartrow((page-1)*10+1);//시작행번호
        nvo.setEndrow(nvo.getStartrow()+limit-1);//끝행번호

        int listCount=this.newsService.getListCount();
        //총게시물수
        List<NewsVO> blist=this.newsService.getBoardList(nvo);
        //게시물 목록

        //총페이지수
        int maxpage=(int)((double)listCount/limit+0.95);
        //현재페이지에 보여질 시작페이지
        int startpage=(((int)((double)page/10+0.9))-1)*10+1;
        //현재 페이지에 보여질 마지막 페이지
        int endpage=maxpage;

        if(endpage>startpage+10-1) endpage=startpage+10-1;
        //마지막 페이지> 시작페이지+10-1   마지막페이지=시작페이지+10-1

        m.addAttribute("list",blist);//list 키이름에
        //blist 를 저장
        m.addAttribute("totalCount",listCount);
        //totalCount 키이름에 listCount저장
        m.addAttribute("startpage",startpage);
        m.addAttribute("endpage",endpage);
        m.addAttribute("maxpage",maxpage);
        m.addAttribute("page",page);//page키이름에 쪽번호
        //를 저장
        return "news/findo_news";
    }
    @RequestMapping(value="/news_write",method= RequestMethod.GET) //GET으로 접근하는 board_write 매핑주소를 처리
    public void news_write(HttpServletRequest request) {//리턴타입이 없는 void형이면 매핑주소가 뷰페이지 파일명이 된다.
        int page=1;
        if(request.getParameter("page") != null) {
            page=Integer.parseInt(request.getParameter("page"));
        }
        request.setAttribute("page",page);//page키이름에 쪽번호 저장=>페이징에서 책갈피기능을 구현
    }

    @RequestMapping(value="/news_write",method=RequestMethod.POST) //post로 접근하는 매핑주소 처리,폼태그에서 액션 속성을 지정하지
    //않으면 이전 매핑주소가 액션 매핑주소가 된다. 같은 매핑주소 구분은 method인 get or post로 구분한다.
    public ModelAndView news_write_ok(NewsVO nvo, RedirectAttributes rttr, MultipartFile[] uploadFile) throws IOException {
        /* BoardVO b는 board_write.jsp의 네임피라미터 이름과 BoardVO 빈클래스의 변수명이 같으면 b객체에 글쓴이와 제목,글내용이 저장되어 있다.
         * 코드라인을 줄이는효과가 발생한다.
         */
        String uploadFolder = "/Users/beomsujeong/Documents/intellij1.8/Jordon/upload/";

        if(uploadFile != null){
            for(MultipartFile multipartFile:uploadFile){
                String uuid = UUID.randomUUID().toString();
                System.out.println("-------------------->");
                System.out.println("Upload FILE NAME : "+ multipartFile.getOriginalFilename()); //업로드 원본 파일명
                System.out.println("Upload File Size : "+ multipartFile.getSize()); // 업로드 파일 크기
                String OriginfileName = (String) multipartFile.getOriginalFilename();// 원래 파일명

                //db에 중복된 이름의 파일을 막기위해 uuid를 붙여서 새로 파일 이름 정의하고 저장함.
                String NewFileName = uuid+multipartFile.getOriginalFilename();
                File saveFile = new File(uploadFolder, NewFileName);

                System.out.println("NewFileName"+NewFileName);
                nvo.setFileoriginname(OriginfileName);
                nvo.setUuidname(NewFileName);
                this.newsService.insertFile(nvo);

                try{
                    multipartFile.transferTo(saveFile);// uploads 폴더에 업로드 되는 원본 파일명으로 실제 업로드
                }catch (Exception e) {
                    System.out.println();
                    e.printStackTrace();
                }
            }

        }else {
            System.out.println("파일이 null");
        }
        this.newsService.insertNews(nvo);//게시물저장

        System.out.println(nvo);
        rttr.addFlashAttribute("msg","SUCCESS");//msg키이름에 SUCCESS문자열로 담아서 서버 컨트롤의 다른매핑주소로 전달한다.이 방법은 웹주소
        //창에서 노출되지 않아서 보안상좋다.
        return new ModelAndView("redirect:news/");//get방식으로 다른 매핑주소인 목록보기로 이동
    }


    @RequestMapping("news_cont") //get or post 방식일때 모두 실행
    public ModelAndView news_cont(@RequestParam("nno") int nno, @RequestParam("page") int page ) {
        /* @RequestParam("nno") 애노테이션은 서블릿의 request.getParameter("nno")와 같은 기능을 한다.결국 nno,page 네임피라미터 이름
         * 에 담겨진 값을 가져온다.
         */

        List<NewsVO> NfileL = this.newsService.getNewsFilesByNno(nno);
        System.out.println(NfileL);
        NewsVO nvo = this.newsService.getNewsCont(nno);//조회수증가와 내용보기
        String cont = nvo.getNcontent().replace("\n","<br/>");//textarea영역에서 엔터키 친부분을 줄바꿈 즉 개행한다.
        ModelAndView cm=new ModelAndView();
        cm.addObject("b",nvo);//b키이름에 b객체를 저장
        cm.addObject("NfileL", NfileL);
        cm.addObject("cont",cont);
        cm.addObject("page",page);//책갈피 기능 구현을 위한 쪽번호 저장
        cm.setViewName("news/news_cont");//뷰페이지 경로
        return cm;
    }
    @RequestMapping("download")
    public ResponseEntity<Object> down_cont(@RequestParam("uuidname") String uuidname, HttpServletResponse response) throws MalformedURLException {
        response.setContentType("text/html;Charset=UTF-8");
        String path = "/Users/Beomsujeong/Documents/intellij1.8/Jordon/upload/";
        //System.out.println("nno:"+nno);
        System.out.println("uuidname:"+uuidname);



        try {
            Path filePath = Paths.get(path+uuidname);
            Resource resource = new InputStreamResource(Files.newInputStream(filePath)); // 파일 resource 얻기

            File file = new File(path);//해당경로에 있는 파일을 찾음
            String originName = (String) this.newsService.getOriginName(uuidname);
            System.out.println("originName"+originName);
            String encodedFilename = URLEncoder.encode(originName, "UTF-8").replaceAll("\\+", "%20"); //한글 파일을 utf-8로 인코딩 하고 띄워쓰기를 '+'f로 나오는걸 공백으로 나오게 함.


            HttpHeaders headers = new HttpHeaders();
            headers.setContentDisposition(ContentDisposition.builder("attachment").filename(encodedFilename).build());  // 다운로드 되거나 로컬에 저장되는 용도로 쓰이는지를 알려주는 헤더


            System.out.println("builder:"+file.getName());
            return new ResponseEntity<Object>(resource, headers, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<Object>(null, HttpStatus.CONFLICT);
        }

    }


    @RequestMapping("news_edit")
    public String news_edit(int nno,int page,HttpServletRequest request) {
        //int bno,int page로 지정하면 get으로 전달된 bno,page피라미터값을 가져옴
        // 여기서 세션 받아와야 함. 받는게 없어서 풀리는거 같음.
        NewsVO eb = newsService.getNewscont2(nno);//조회수를 증가하지 않고 번호에 해당하는 레코드를 가져오기
        request.setAttribute("eb",eb);
        request.setAttribute("page",page);
        return "news/news_edit"; //뷰리졸브 경로가 WEB-INF/views/board/board_edit.jsp
    }

    @RequestMapping("news_edit_ok") //get or post 양쪽방식으로 접근하는 board_edit_ok 매핑주소 처리
    public ModelAndView news_edit_ok(int nno,int page,NewsVO eb) {
        /* get으로 전달된 번호와 쪽번호는 int bno,int page로 받고,수정할 글쓴이,제목,글내용은 post방식으로 전달된것은 BoardVO eb로 받는다.
         * eb로 받을려고 네임피라미터 이름과 빈클래스 변수명을 같게 한다.
         */
        eb.setNno(nno);//번호값을 저장
        this.newsService.updateBoard(eb);//번호를 기준으로 제목,글쓴이,글내용을 수정

        ModelAndView em=new ModelAndView();
        em.setViewName("redirect:/news/news");//매핑주소 경로 설정
        em.addObject("nno",nno);
        em.addObject("page",page);
        return em;//주소창에 매핑주소가 다음과 같이 설정된다. /board/board_cont?bno=번호&page=쪽번호 get방식으로 전달된다.내용보기에서
        //수정한 값을 확인할 수 있다.
    }//board_edit_ok()
    @GetMapping("news_del") //board_del 매핑주소 등록, get으로 접근하는 매핑주소를 처리
    public ModelAndView news_del(int nno,int page,RedirectAttributes rttr) {
        //int bno,int page하면 피라미터네임 bno,page로 전달된 번호값과 쪽번호를 가져온다.

        newsService.deleteNews(nno);//게시물 삭제
        /* 문제)번호를 기준으로 삭제되게 서비스,모델 DAOImpl,mybatis 매퍼태그까지 선순환 구조를 완성해 본다.더불어 개발자 테스트(디버깅)까지
         * 해본다.
         */

        rttr.addFlashAttribute("msg","SUCCESS");

        ModelAndView dm=new ModelAndView();
        dm.setViewName("redirect:/news/news?page="+page);
        return dm;//  /board/board_list?page=쪽번호 가 get으로 전달
    }//board_del()
}
