package com.project.jordon.dao;

import com.project.jordon.vo.FaqVO;

import java.util.List;

public interface FaqDAO {

    void insertBoard(FaqVO f);
    int getTotalCount();
    List<FaqVO> getBoardList(FaqVO f);
    void updateHit(int fbno);
    FaqVO getBoardCont(int fbno);
    void updateBoard(FaqVO eb);
    void deleteBoard(int fbno);
}



