package com.project.jordon.service;

import com.project.jordon.vo.BoardVO;
import com.project.jordon.vo.NewsVO;

import java.util.List;

public interface NewsService {
    int getListCount();

    List<NewsVO> getBoardList(NewsVO nvo);

    void insertNews(NewsVO newsVO);

    NewsVO getNewsCont(int nno);

    NewsVO getNewscont2(int nno);

    void updateBoard(NewsVO eb);

    void deleteNews(int nno);

    List<NewsVO> getNewsFilesByNno(int nno);

    void insertFile(NewsVO nvo);

    Object getOriginName(String uuidname);
}
