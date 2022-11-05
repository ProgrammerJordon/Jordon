package com.project.jordon.service;

import com.project.jordon.vo.AnnouncementVO;

import java.util.List;

public interface AnnounceService {
    void insertBoard(AnnouncementVO a);
    int getTotalCount();

    int getListCount();

    List<AnnouncementVO> getBoardList(AnnouncementVO a);
    AnnouncementVO getBoardCont(int abno);
    AnnouncementVO getBoardCont2(int abno);
    void updateBoard(AnnouncementVO eb);
    void deleteBoard(int abno);

}
