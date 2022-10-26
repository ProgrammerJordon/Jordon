package com.project.jordon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.project.jordon.dao.BoardDAO;
import com.project.jordon.vo.BoardVO;

@Service //@Service 애노테이션이 추가되면 스프링에서 서비스로 인식한다.(스프링 MVC게시판)
public class BoardServiceImpl implements BoardService {

    @Autowired //자동 의존성 주입
    private BoardDAO boardDao;

    @Override
    public void insertBoard(BoardVO b) {
        this.boardDao.insertBoard(b);//this.은 생략이 가능함
    }

    @Override
    public int getListCount() {
        return boardDao.getTotalCount();
    }

    @Override
    public List<BoardVO> getBoardList(BoardVO b) {
        return this.boardDao.getBoardList(b);
    }

    //스프링의 aop를 통한 트랜잭션 적용대상
    @Transactional(isolation = Isolation.READ_COMMITTED)
    //트랜잭션 격리(트랜잭션이 처리되는 중간에 외부간섭을 제거. READ_COMMITTED는 커밋된 데이터에 대해 읽기 허용
    @Override
    public BoardVO getBoardCont(int bno) {
        this.boardDao.updateHit(bno);//조회수 증가
        return this.boardDao.getBoardCont(bno);//번호에 해당하는 내용보기
    }

    @Override
    public BoardVO getBoardCont2(int bno) {
        return boardDao.getBoardCont(bno);
    }//조회수를 증가하지 않고 내용만 가져오기

    @Override
    public void updateBoard(BoardVO eb) {
        this.boardDao.updateBoard(eb);
    }

    @Override
    public void deleteBoard(int bno) {
        this.boardDao.deleteBoard(bno);
    }
}
