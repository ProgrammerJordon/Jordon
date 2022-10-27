package com.project.jordon.dao;

import com.project.jordon.vo.PortfolioVO;
import com.project.jordon.vo.SearchVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PortfolioDAOImpl implements PortfolioDAO{
    @Autowired
    SqlSession sqlSession;
    @Override
    public List<PortfolioVO> getPortfolioList(PortfolioVO pvo) {
        return sqlSession.selectList("portfolioList",pvo);
    }

    @Override
    public List<SearchVO> getSearchList(SearchVO svo) {
        return sqlSession.selectList("portSearchList", svo);
    }

    @Override
    public PortfolioVO checkPortfolio(PortfolioVO pvo) {
        return sqlSession.selectOne("checkStockExist", pvo);
    }

    @Override
    public void addPortfolio(PortfolioVO pvo) {
        this.sqlSession.insert("insertPortfolio", pvo);
    }

    @Override
    public void editAvgPrice(PortfolioVO pvo) {
        this.sqlSession.update("editAvg", pvo);
    }
}
