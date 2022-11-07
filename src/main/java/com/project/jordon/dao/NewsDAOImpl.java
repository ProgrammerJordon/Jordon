package com.project.jordon.dao;

import com.project.jordon.vo.NewsVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NewsDAOImpl implements NewsDAO{
    @Autowired
    SqlSession sqlSession;


    @Override
    public int getListCount() {
        return sqlSession.selectOne("listCount");
    }

    @Override
    public List<NewsVO> getBoardList(NewsVO nvo) {
        return sqlSession.selectList("getNewsList");
    }

    @Override
    public void insertNews(NewsVO nvo) {
        sqlSession.insert("insertNews", nvo);
    }

    @Override
    public NewsVO getNewsCont(int nno) {
        return sqlSession.selectOne("getNewCont", nno);
    }

    @Override
    public void updateHit(int nno) {
        sqlSession.update("updateNewsHit", nno);
    }

    @Override
    public void updateBoard(NewsVO eb) {
        sqlSession.update("updateNews", eb);
    }

    @Override
    public void deleteNews(int nno) {
        sqlSession.delete("delNews", nno);
    }

    @Override
    public List<NewsVO> getNewsFileByNno(int nno) {
        return sqlSession.selectList("getFiles", nno);
    }

    @Override
    public void insertFile(NewsVO nvo) {
        sqlSession.insert("insertFiles", nvo);
    }

    @Override
    public Object getOriginName(String uuidname) {
        return sqlSession.selectOne("getOriginName", uuidname);
    }
}
