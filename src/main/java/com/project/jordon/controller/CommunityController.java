package com.project.jordon.controller;

import com.project.jordon.service.CommunityService;
import com.project.jordon.vo.CommunityVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CommunityController {

    @Autowired
    private CommunityService communityservice;

    // 커뮤니티 리스트 보여주는 컨트롤러
    @RequestMapping("/community")
    public String community() {
        return "findo_community";
    }

    @RequestMapping("/community_contents")
    public String community_contents() {
        return "findo_community_contents";
    }

    // 커뮤니티 글쓰기로 넘어가는 컨트롤러
    @RequestMapping("/community_write")
    public String community_write() {
        return "findo_community_write";
    }

    // 커뮤니티 글쓰기 확정 버튼 클릭 컨트롤러
    @RequestMapping("/community_write_ok")
    public String community_write_ok(CommunityVO b) {
        if (b.getCommunitywriter() != null && b.getCommunitytitle() != null & b.getCommunitycontents() != null) {
            this.communityservice.insercommunity(b);
            System.out.println("글쓰기 저장 완료");
            return "redirect:/community";
        } else {
            System.out.println("글쓰기 실패");
            return "redirect:/community_write";
        }
    }

    // 커뮤니티 글쓰기에서 수정하기로 넘어가는 컨트롤러
    @RequestMapping("/community_edit")
    public String community_edit() {
        return "findo_community_edit";
    }

    // 커뮤니티 글 수정 완료후 글내용으로 넘어가는 컨트롤러
    @RequestMapping("/community_edit_ok")
    public String community_edit_ok(CommunityVO b) {
        if (b.getCommunitytitle() != null && b.getCommunitycontents() != null) {
            this.communityservice.updatecommunity(b);
            System.out.println("게시글 수정완료");
            return "redirect:/community";
        }
        return "redirect:/community_edit";
    }

    // 커뮤니티 글 삭제 정보 받는 페이지 컨트롤러
    @RequestMapping("/community_delete")
    public String community_delete() {
        return "findo_community_delete";
    }

    @RequestMapping("/community_delete_ok")
    public String community_delete_ok() {
        return "redirect:/community";
    }
}
