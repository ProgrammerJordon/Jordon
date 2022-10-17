package com.project.jordon;

import com.project.jordon.dao.BoardRepository;
import com.project.jordon.vo.BoardVO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
class BoardRepositoryTest {
    @Autowired
    private BoardRepository boardRepo;

    @Test
    public void testInsert() { // 레코드 저장
        BoardVO b = new BoardVO();

        b.setWriter("이순신");
        b.setTitle("글제목입니다.");
        b.setContent("글내용입니다.");

        //this.boardRepo.save(b); // 레코드 저장
    }//testInsert()

    // 1번 레코드 검색
    @Test
    public void testRead() {
        Optional<BoardVO> b;
        b = this.boardRepo.findById(1L);
        System.out.println(b); // ToString() 생략됨
    }
}
