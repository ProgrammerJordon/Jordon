package com.project.jordon.service;


import com.project.jordon.dao.AnnounceDAO;
import com.project.jordon.vo.AnnouncementVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service //@Service 애노테이션이 추가되면 스프링에서 서비스로 인식한다.(스프링 MVC게시판)
public class AnnounceServiceImpl implements AnnounceService {

    @Autowired //자동 의존성 주입
    private AnnounceDAO announceDao;

    @Override
    public void insertBoard(AnnouncementVO a) {
        this.announceDao.insertBoard(a);//this.은 생략이 가능함
    }

    @Override
    public int getTotalCount() {
        return this.announceDao.getTotalCount();
    }

    @Override
    public int getListCount() {
        return announceDao.getTotalCount();
    }

    @Override
    public List<AnnouncementVO> getBoardList(AnnouncementVO a) {
        return this.announceDao.getBoardList(a);
    }


    //스프링의 aop를 통한 트랜잭션 적용대상
    @Transactional(isolation = Isolation.READ_COMMITTED)
    //트랜잭션 격리(트랜잭션이 처리되는 중간에 외부간섭을 제거. READ_COMMITTED는 커밋된 데이터에 대해 읽기 허용
    @Override
    public AnnouncementVO getBoardCont(int abno) {
        this.announceDao.updateHit(abno);//조회수 증가
        return this.announceDao.getBoardCont(abno);//번호에 해당하는 내용보기
    }

    @Override
    public AnnouncementVO getBoardCont2(int abno) {
        return announceDao.getBoardCont(abno);
    }//조회수를 증가하지 않고 내용만 가져오기

    @Override
    public void updateBoard(AnnouncementVO eb) {
        this.announceDao.updateBoard(eb);
    }

    @Override
    public void deleteBoard(int abno) {
        this.announceDao.deleteBoard(abno);
    }

}
