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
    public void insercommunity(CommunityVO i) {
        this.sqlSession.insert("insert_community",i);
    }

    @Override
    public void updatecommunity(CommunityVO i) {
        this.sqlSession.update("update_community", i);
    }

    @Override
    public void deletecommunity(CommunityVO i) {
        this.sqlSession.delete("delete_community", i);
    }


}
