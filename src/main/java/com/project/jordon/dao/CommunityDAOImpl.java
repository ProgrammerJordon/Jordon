package com.project.jordon.dao;

import com.project.jordon.vo.CommunityVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommunityDAOImpl implements CommunityDAO{

    @Autowired
    SqlSession sqlSession;

    @Override
    public void insercommunity(CommunityVO b) {
        this.sqlSession.insert("insert_community",b);
    }

    @Override
    public void updatecommunity(CommunityVO b) {
        this.sqlSession.update("update_community", b);
    }



}
