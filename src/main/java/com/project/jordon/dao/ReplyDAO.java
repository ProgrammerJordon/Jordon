package com.project.jordon.dao;

import java.util.List;

import com.project.jordon.vo.ReplyVO;

public interface ReplyDAO {

    void insertReply(ReplyVO vo);
    List<ReplyVO> replyList(int bno);
    void updateReply(ReplyVO vo);
    void delReply(int rno);
    int getBno(int rno);

}
