package com.project.jordon.dao;

import com.project.jordon.vo.MemberVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAOImpl implements MemberDAO {

    @Autowired
    SqlSession sqlSession;

    @Override
    public void insertMember(MemberVO m) {this.sqlSession.insert("member_insert", m);
    }

    @Override
    public MemberVO loginMember(String memberid) {
        return this.sqlSession.selectOne("member_login", memberid);
    }

    @Override
    public MemberVO idsearchMember(String memberemail) {
        return this.sqlSession.selectOne("member_id_search", memberemail);
    }

    @Override
    public MemberVO passwordsearchMember(String memberid) {
        return this.sqlSession.selectOne("member_password_search", memberid);
    }

    @Override
    public int updateMember(MemberVO m) {
        return this.sqlSession.update("member_update", m);
    }

    @Override
    public int deleteMember(MemberVO m) {
        return this.sqlSession.delete("member_delete", m);
    }

    @Override
    public int idcheck(String memberid) {
        return this.sqlSession.selectOne("idcheck", memberid);
    }

}
