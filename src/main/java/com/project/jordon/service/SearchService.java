package com.project.jordon.service;

import com.project.jordon.vo.SearchVO;

import java.util.List;

public interface SearchService {
    List<SearchVO> getSearchList(SearchVO svo);

    SearchVO getSearchCont(String stocknumber);
}