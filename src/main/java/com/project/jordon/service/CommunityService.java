package com.project.jordon.service;

import com.project.jordon.vo.CommunityVO;

import java.util.List;

public interface CommunityService {
    // 커뮤니티 관련 기능 --> 컨트롤러 연결되어 있다.
    void insercommunity(CommunityVO b);
    // 커뮤니티 글 수정
    void updatecommunity(CommunityVO b);
    // 커뮤니티 글 보여주기
}
