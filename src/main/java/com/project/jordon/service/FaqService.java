package com.project.jordon.service;

import com.project.jordon.vo.FaqVO;

import java.util.List;

public interface FaqService {

    void insertBoard(FaqVO f);
    int getTotalCount();

    int getListCount();

    List<FaqVO> getBoardList(FaqVO f);
    void updateHit(int fbno);
    FaqVO getBoardCont(int fbno);

    FaqVO getBoardCont2(int fbno)//조회수를 증가하지 않고 내용만 가져오기
    ;

    void updateBoard(FaqVO eb);
    void deleteBoard(int fbno);
}
