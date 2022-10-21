package com.project.jordon.service;

import com.project.jordon.dao.SearchDAO;
import com.project.jordon.vo.SearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SearchServiceImpl implements SearchService{
    @Autowired
    SearchDAO searchDAO;

    @Override
    public List<SearchVO> getSearchList(SearchVO svo) {
        return  searchDAO.getSearchList(svo);
    }

    @Override
    public SearchVO getSearchCont(String stocknumber) {
        return searchDAO.getSearchCont(stocknumber);
    }
}