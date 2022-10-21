package com.project.jordon.dao;

import com.project.jordon.vo.SearchVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SearchDAOImpl implements SearchDAO {

    @Autowired
    SqlSession session;

    @Override
    public List<SearchVO> getSearchList(SearchVO svo) {
        return this.session.selectList("search_list", svo);
    }

    @Override
    public SearchVO getSearchCont(String stocknumber) {
        return this.session.selectOne("search_cont", stocknumber);
    }
}
