package com.project.jordon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.jordon.dao.BoardDAO;
import com.project.jordon.dao.ReplyDAO;
import com.project.jordon.vo.ReplyVO;

@Service //@Service 애노테이션을 추가함으로써 스프링에 서비스라는 것을 인식하게 한다.
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private ReplyDAO replyDao;

    @Autowired
    private BoardDAO boardDao;

    //스프링의 aop를 통한 트랜잭션 적용부분
    @Transactional //트랜잭션 적용함으로서 데이터의 일관성(일치성)을 보장
    @Override
    public void insertReply(ReplyVO vo) {
        this.replyDao.insertReply(vo);
        this.boardDao.updateReplyCnt(vo.getBno(),1);//게시판 번호값을 구한 다음 댓글이 추가되면 댓글 개수를 1증가해서 replycnt컬럼 레코드
        //수정
    }//댓글 추가+댓글 카운터 업데이트

    @Override
    public List<ReplyVO> replyList(int bno) {
        return this.replyDao.replyList(bno);
    }

    @Override
    public void updateReply(ReplyVO vo) {
        replyDao.updateReply(vo);
    }

    @Transactional //트랜잭션 적용
    @Override
    public void delReply(int rno) {
        int bno=this.replyDao.getBno(rno);//댓글 번호를 기준으로 게시판 번호값을 구함
        this.replyDao.delReply(rno);
        this.boardDao.updateReplyCnt(bno,-1);//댓글이 삭제되면 댓글 개수가 1 감소
    }//댓글 삭제와 댓글 카운터 1감소
}

