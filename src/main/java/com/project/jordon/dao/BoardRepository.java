package com.project.jordon.dao;

import com.project.jordon.vo.BoardVO;
import org.springframework.data.repository.CrudRepository;

public interface BoardRepository extends CrudRepository<BoardVO, Long> {
    // 모델DAOImpl의 Repository 인터페이스 설계

}

