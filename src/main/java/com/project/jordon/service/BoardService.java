package com.project.jordon.service;

import java.util.List;

import com.project.jordon.vo.BoardVO;

public interface BoardService {

    void insertBoard(BoardVO b);
    int getListCount();
    List<BoardVO> getBoardList(BoardVO b);
    BoardVO getBoardCont(int bno);
    BoardVO getBoardCont2(int bno);
    void updateBoard(BoardVO eb);
    void deleteBoard(int bno);

}
