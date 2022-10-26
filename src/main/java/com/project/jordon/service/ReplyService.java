package com.project.jordon.service;

import java.util.List;

import com.project.jordon.vo.ReplyVO;

public interface ReplyService {

    void insertReply(ReplyVO vo);
    List<ReplyVO> replyList(int bno);
    void updateReply(ReplyVO vo);
    void delReply(int rno);

}
