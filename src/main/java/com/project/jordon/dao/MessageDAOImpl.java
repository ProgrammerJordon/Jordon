package com.project.jordon.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.jordon.vo.MessageVO;

@Repository
public class MessageDAOImpl implements MessageDAO { //모델 DAOImpl

    @Autowired
    private SqlSession sqlSession;

    @Override
    public void insertM(MessageVO vo) {
        this.sqlSession.insert("m_in2",vo);
        //insert()로 레코드를 추가하고, m_in2는 매퍼태그에서 설정할 유일한 아이디명
    }//메시지 추가
}
