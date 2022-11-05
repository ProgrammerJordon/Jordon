package com.project.jordon.dao;

import com.project.jordon.vo.AnnouncementVO;

import java.util.List;

public interface AnnounceDAO {

    void insertBoard(AnnouncementVO a);
    int getTotalCount();
    List<AnnouncementVO> getBoardList(AnnouncementVO a);
    void updateHit(int abno);
    AnnouncementVO getBoardCont(int abno);
    void updateBoard(AnnouncementVO eb);
    void deleteBoard(int abno);
}


