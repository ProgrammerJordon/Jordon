package com.project.jordon.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.jordon.vo.ReplyVO;

@Repository //@Repository 애노테이션을 추가함 으로써 스프링에 모델 DAO라는 것을 인식하게 한다.
public class ReplyDAOImpl implements ReplyDAO {

    @Autowired //자동의존성 주입
    private SqlSession sqlSession; //mybatis 쿼리문 수행 sqlSession

    @Override
    public void insertReply(ReplyVO vo) {
        this.sqlSession.insert("reply_in",vo);//insert()로 레코드를 저장하고,reply_in은 reply.xml에서 설정할 유일한 아이디명.
    }//댓글 저장

    @Override
    public List<ReplyVO> replyList(int bno) {
        return this.sqlSession.selectList("reply_list",bno);//selectList()는 하나이상의 레코드를 검색, reply_list는 유일한 아이
        //디명(reply.xml)
    }//댓글 목록

    @Override
    public void updateReply(ReplyVO vo) {
        sqlSession.update("reply_edit",vo);//update()메서드로 레코드를 수정. reply_edit는 유일한 update아이디명(reply.xml)
    }//댓글 수정

    @Override
    public void delReply(int rno) {
        this.sqlSession.delete("reply_del", rno);//delete()메서드로 레코드를 삭제, reply_del은 reply.xml에서 설정할 유일한 아이디명
    }//댓글 삭제

    @Override
    public int getBno(int rno) {
        return this.sqlSession.selectOne("reply_bno",rno);//selectOne()메서드는 단 하나의 레코드값만 반환, reply_bno는 매퍼태그에서
        //설정할 유일한 아이디명
    }//댓글 번호에 해당하는 게시판 번호 구하기
}
