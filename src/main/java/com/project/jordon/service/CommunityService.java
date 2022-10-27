package com.project.jordon.service;

import com.project.jordon.vo.CommunityVO;

import java.util.List;

public interface CommunityService {
    // 커뮤니티 관련 기능 --> 컨트롤러 연결되어 있다.
    void insercommunity(CommunityVO i);
    // 커뮤니티 글 수정
    void updatecommunity(CommunityVO i);
    // 커뮤니티 글 보여주기

    // 커뮤니티 삭제 하는 컨트롤러로  보내줄 메소드
    void deletecommunity(CommunityVO i);
}
