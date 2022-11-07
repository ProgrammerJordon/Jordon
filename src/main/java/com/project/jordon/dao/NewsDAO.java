package com.project.jordon.dao;

import com.project.jordon.vo.NewsVO;

import java.util.List;

public interface NewsDAO {
    int getListCount();

    List<NewsVO> getBoardList(NewsVO nvo);

    void insertNews(NewsVO nvo);

    NewsVO getNewsCont(int nno);

    void updateHit(int nno);

    void updateBoard(NewsVO eb);

    void deleteNews(int nno);

    List<NewsVO> getNewsFileByNno(int nno);

    void insertFile(NewsVO nvo);

    Object getOriginName(String uuidname);
}
