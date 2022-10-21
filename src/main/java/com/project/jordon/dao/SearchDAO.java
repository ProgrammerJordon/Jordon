package com.project.jordon.dao;

import com.project.jordon.vo.SearchVO;

import java.util.List;

public interface SearchDAO {
    List<SearchVO> getSearchList(SearchVO svo);

    SearchVO getSearchCont(String stocknumber);
}
