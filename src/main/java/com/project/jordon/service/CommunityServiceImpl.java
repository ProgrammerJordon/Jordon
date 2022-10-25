package com.project.jordon.service;

import com.project.jordon.dao.CommunityDAO;
import com.project.jordon.vo.CommunityVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommunityServiceImpl implements CommunityService{

    @Autowired
    private CommunityDAO communityDAO;

    @Override
    public void insercommunity(CommunityVO b) {
        this.communityDAO.insercommunity(b);
    }

    @Override
    public void updatecommunity(CommunityVO b) {
        this.communityDAO.updatecommunity(b);
    }




}
