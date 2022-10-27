package com.project.jordon.dao;

import com.project.jordon.vo.CommunityVO;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface CommunityDAO {
    // 커뮤니티 게시글 저장
    void insercommunity(CommunityVO i);
    // 커뮤니티 게시글 수정 업데이트
    void updatecommunity(CommunityVO i);
    // 커뮤니티 목록을 보여줌

    void deletecommunity(CommunityVO i);

}
