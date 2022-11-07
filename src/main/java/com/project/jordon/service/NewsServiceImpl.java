package com.project.jordon.service;

import com.project.jordon.dao.NewsDAO;
import com.project.jordon.vo.BoardVO;
import com.project.jordon.vo.NewsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService{
    @Autowired
    NewsDAO newsDAO;
    @Override
    public int getListCount() {
        return newsDAO.getListCount();
    }

    @Override
    public List<NewsVO> getBoardList(NewsVO nvo) {
        return newsDAO.getBoardList(nvo);
    }

    @Override
    public void insertNews(NewsVO nvo) {

        newsDAO.insertNews(nvo);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public NewsVO getNewsCont(int nno) {
        this.newsDAO.updateHit(nno);
        return newsDAO.getNewsCont(nno);
    }

    @Override
    public NewsVO getNewscont2(int nno) {
        return this.newsDAO.getNewsCont(nno);
    }

    @Override
    public void updateBoard(NewsVO eb) {
        this.newsDAO.updateBoard(eb);
    }

    @Override
    public void deleteNews(int nno) {
        this.newsDAO.deleteNews(nno);
    }

    @Override
    public List<NewsVO> getNewsFilesByNno(int nno) {
        return this.newsDAO.getNewsFileByNno(nno);
    }

    @Override
    public void insertFile(NewsVO nvo) {
        newsDAO.insertFile(nvo);
    }

    @Override
    public Object getOriginName(String uuidname) {
        return this.newsDAO.getOriginName(uuidname);
    }


}
