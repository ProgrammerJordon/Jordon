package com.project.jordon.dao;

import com.project.jordon.vo.PortfolioVO;
import com.project.jordon.vo.SearchVO;

import java.util.List;

public interface PortfolioDAO {
    List<PortfolioVO> getPortfolioList(PortfolioVO pvo);

    List<SearchVO> getSearchList(SearchVO svo);

    PortfolioVO checkPortfolio(PortfolioVO pvo);

    void addPortfolio(PortfolioVO pvo);

    void editAvgPrice(PortfolioVO pvo);
}
