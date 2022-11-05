package com.project.jordon.service;


import com.project.jordon.dao.FaqDAO;
import com.project.jordon.vo.FaqVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service //@Service 애노테이션이 추가되면 스프링에서 서비스로 인식한다.(스프링 MVC게시판)
public class FaqServiceImpl implements FaqService {

    @Autowired //자동 의존성 주입
    private FaqDAO faqDao;

    @Override
    public void insertBoard(FaqVO f) {
        this.faqDao.insertBoard(f);//this.은 생략이 가능함
    }

    @Override
    public int getTotalCount() {
        return this.faqDao.getTotalCount();
    }

    @Override
    public int getListCount() {
        return faqDao.getTotalCount();
    }

    @Override
    public List<FaqVO> getBoardList(FaqVO f) {
        return this.faqDao.getBoardList(f);
    }

    @Override
    public void updateHit(int fbno) {

    }


    //스프링의 aop를 통한 트랜잭션 적용대상
    @Transactional(isolation = Isolation.READ_COMMITTED)
    //트랜잭션 격리(트랜잭션이 처리되는 중간에 외부간섭을 제거. READ_COMMITTED는 커밋된 데이터에 대해 읽기 허용
    @Override
    public FaqVO getBoardCont(int fbno) {
        this.faqDao.updateHit(fbno);//조회수 증가
        return this.faqDao.getBoardCont(fbno);//번호에 해당하는 내용보기
    }

    @Override
    public FaqVO getBoardCont2(int fbno) {
        return faqDao.getBoardCont(fbno);
    }//조회수를 증가하지 않고 내용만 가져오기

    @Override
    public void updateBoard(FaqVO eb) {
        this.faqDao.updateBoard(eb);
    }

    @Override
    public void deleteBoard(int fbno) {
        this.faqDao.deleteBoard(fbno);
    }

}
