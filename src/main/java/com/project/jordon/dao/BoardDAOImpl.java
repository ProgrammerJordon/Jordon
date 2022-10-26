package com.project.jordon.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.jordon.vo.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO {

    @Autowired //자동 의존성 주입
    private SqlSession sqlSession; //mybatis 쿼리문 수행 sqlSession

    @Override
    public void insertBoard(BoardVO b) {
        this.sqlSession.insert("b_in",b);//insert()메서드로 레코드를 저장하고,b_in은 board.xml에서 설정할  유일 아이디명
    }//게시물 저장

    @Override
    public int getTotalCount() {
        return sqlSession.selectOne("b_count");//mybatis에서 selectOne()메서드는 단 한개의 레코드만 반환하고 b_count는 board.xml
        //에서 설정할 유일한 아이디명
    }//총 레코드 개수

    @Override
    public List<BoardVO> getBoardList(BoardVO b) {
        return this.sqlSession.selectList("b_list",b);//mybatis에서 selectList()메서드는 하나이상의 레코드를 반환하고 b_list는
        //board.xml에서 설정할 유일 아이디명
    }//게시물 목록

    @Override
    public void updateHit(int bno) {
        this.sqlSession.update("b_hit",bno);//mybatis에서 update()메서드는 레코드를 수정하고, b_hit는 board.xml에서 유일 아이디명이다.
    }//조회수 증가

    @Override
    public BoardVO getBoardCont(int bno) {
        return this.sqlSession.selectOne("b_cont",bno);
    }//내용보기

    @Override
    public void updateBoard(BoardVO eb) {
        this.sqlSession.update("b_edit",eb);//b_edit는 board.xml에서 설정할 유일한 update 아이디명,
    }//게시물 수정

    @Override
    public void deleteBoard(int bno) {
        this.sqlSession.delete("b_del",bno);//mybatis에서 delete()메서드로 레코드를 삭제하고, b_del은 유일한 delete 아이디명
    }//게시물 삭제

    @Override
    public void updateReplyCnt(int bno, int count) {
        Map<String,Object> pm=new HashMap<>();

        pm.put("bno",bno);//키,값쌍으로 bno키이름에 게시판 번호 저장
        pm.put("count",count);

        this.sqlSession.update("updateReplyCnt", pm);//updateReplyCnt는 매퍼태그에서 설정할 유일한 update아이디명
    }//댓글 카운터 업데이트
}

