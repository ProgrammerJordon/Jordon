package com.project.jordon.dao;

import java.util.List;

import com.project.jordon.vo.BoardVO;

public interface BoardDAO {

    void insertBoard(BoardVO b);
    int getTotalCount();
    List<BoardVO> getBoardList(BoardVO b);
    void updateHit(int bno);
    BoardVO getBoardCont(int bno);
    void updateBoard(BoardVO eb);
    void deleteBoard(int bno);
    void updateReplyCnt(int bno, int count);

}

