package com.project.jordon.dao;

import com.project.jordon.vo.AnnouncementVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AnnounceDAOImpl implements AnnounceDAO {

    @Autowired //자동 의존성 주입
    private SqlSession sqlSession; //mybatis 쿼리문 수행 sqlSession

    @Override
    public void insertBoard(AnnouncementVO a) {
        this.sqlSession.insert("a_in",a);//insert()메서드로 레코드를 저장하고,b_in은 board.xml에서 설정할  유일 아이디명
    }//게시물 저장

    @Override
    public int getTotalCount() {
        return sqlSession.selectOne("a_count");//mybatis에서 selectOne()메서드는 단 한개의 레코드만 반환하고 b_count는 board.xml
        //에서 설정할 유일한 아이디명
    }//총 레코드 개수

    @Override
    public List<AnnouncementVO> getBoardList(AnnouncementVO a) {
        return this.sqlSession.selectList("a_list",a);//mybatis에서 selectList()메서드는 하나이상의 레코드를 반환하고 b_list는
        //board.xml에서 설정할 유일 아이디명
    }//게시물 목록

    @Override
    public void updateHit(int abno) {
        this.sqlSession.update("a_hit",abno);//mybatis에서 update()메서드는 레코드를 수정하고, b_hit는 board.xml에서 유일 아이디명이다.
    }//조회수 증가

    @Override
    public AnnouncementVO getBoardCont(int abno) {
        return this.sqlSession.selectOne("a_cont",abno);
    }//내용보기

    @Override
    public void updateBoard(AnnouncementVO eb) {
        this.sqlSession.update("a_edit",eb);//b_edit는 board.xml에서 설정할 유일한 update 아이디명,
    }//게시물 수정

    @Override
    public void deleteBoard(int abno) {
        this.sqlSession.delete("a_del",abno);//mybatis에서 delete()메서드로 레코드를 삭제하고, b_del은 유일한 delete 아이디명
    }//게시물 삭제

}

